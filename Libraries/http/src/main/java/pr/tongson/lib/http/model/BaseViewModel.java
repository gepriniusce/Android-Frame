package pr.tongson.lib.http.model;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import pr.tongson.library.utils.LogUtils;

/**
 * @param <M>
 * @param <D>
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description UI层
 */
public abstract class BaseViewModel<M extends BaseRepositoryModel, D> extends ViewModel implements LifecycleObserver, IViewModelResult<D> {
    protected M model;
    public MutableLiveData<List<D>> mDataList = new MediatorLiveData<>();
    public MutableLiveData<State> mState = new MediatorLiveData<>();
    public MutableLiveData<String> mErrorMsg = new MediatorLiveData<>();

    public BaseViewModel() {
        mDataList.setValue(new ArrayList<>());
        mState.setValue(State.WAITING);
        mErrorMsg.setValue("");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        LogUtils.i("onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LogUtils.i("onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        LogUtils.i("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LogUtils.i("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtils.i("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogUtils.i("onDestroy");
    }

    //    @Override
    //    public void onSuccess(D d, boolean isFromCache) {
    //
    //    }
    //
    //    @Override
    //    public void onFailure(Throwable e) {
    //
    //    }
}
