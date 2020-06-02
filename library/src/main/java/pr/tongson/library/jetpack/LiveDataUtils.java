package pr.tongson.library.jetpack;

import android.os.Looper;

import java.util.HashMap;
import java.util.Map;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/20
 * @Version
 * @Since
 * @Description 用单例来管理MutableLiveData
 */
public class LiveDataUtils {
    private static LiveDataUtils ourInstance = new LiveDataUtils();

    /**
     * 应用内所有的数据持有的集合
     */
    private Map<String, HookMutableLiveDate<Object>> mMap;

    public static LiveDataUtils getInstance() {
        return ourInstance;
    }

    private LiveDataUtils() {
        mMap = new HashMap<>();
    }

    /**
     * 创建/得到MutableLiveData
     *
     * @param key
     * @param calzz
     * @param <T>
     * @return key所对应的MutableLiveData
     */
    public <T> HookMutableLiveDate<T> with(String key, Class<T> calzz) {
        if (!mMap.containsKey(key)) {
            mMap.put(key, new HookMutableLiveDate<>());
        }

        return (HookMutableLiveDate<T>) mMap.get(key);
    }

    public HookMutableLiveDate<Object> with(String target) {
        return with(target, Object.class);
    }

    public void remove(String key) {
        if (mMap.containsKey(key)) {
            mMap.remove(key);
        }
    }

    public <T> void post(String key, T t) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            with(key).setValue(t);
        } else {
            with(key).postValue(t);
        }
    }
}
