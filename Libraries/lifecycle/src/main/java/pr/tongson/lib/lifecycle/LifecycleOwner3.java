package pr.tongson.lib.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/12
 * @Version
 * @Since
 * @Description
 */
public class LifecycleOwner3 implements LifecycleOwner {

    private LifecycleRegistry mLifecycleRegistry;

    public LifecycleOwner3() {
        init();
    }

    private void init() {
        mLifecycleRegistry = new LifecycleRegistry(this);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    public void onCreate() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    public void onStart() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    public void onResume() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    public void onPause() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    public void onStop() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public void onDestroy() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }
}
