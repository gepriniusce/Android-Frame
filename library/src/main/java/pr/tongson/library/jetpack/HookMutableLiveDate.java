package pr.tongson.library.jetpack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import pr.tongson.library.utils.LogUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/30
 * @Version
 * @Since
 * @Description
 */
public class HookMutableLiveDate<T> extends MutableLiveData<T> {
    /**
     * 目的：使得在observe被调用的一刻，能够保证if(observer.mLastVersion>=mVersion)条件成立
     */
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        try {
            hook(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理粘性事件
     */
    public void observeStick(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
    }

    /**
     * 通过 hook将LastVersion==mVersion
     *
     * @param observer
     */
    private void hook(@NonNull Observer<? super T> observer) throws Exception {
        Class<LiveData> classLiveData = LiveData.class;
        Field fieldObservers = classLiveData.getDeclaredField("mObservers");
        fieldObservers.setAccessible(true);
        Object mObservers = fieldObservers.get(this);
        Class<?> classObservers = mObservers.getClass();

        Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
        methodGet.setAccessible(true);
        Object entry = methodGet.invoke(mObservers, observer);
        Object objectWrapper = ((Map.Entry) entry).getValue();
        //observer
        Class<?> mObserver = objectWrapper.getClass().getSuperclass();
        Field mLastVersion = mObserver.getDeclaredField("mLastVersion");
        mLastVersion.setAccessible(true);
        Field mVersion = classLiveData.getDeclaredField("mVersion");
        mVersion.setAccessible(true);
        Object mVersionValue = mVersion.get(this);
        mLastVersion.set(objectWrapper, mVersionValue);
        LogUtils.i("Tongson hook:" + mVersionValue);
    }
}
