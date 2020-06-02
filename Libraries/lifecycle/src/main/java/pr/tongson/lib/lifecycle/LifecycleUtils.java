package pr.tongson.lib.lifecycle;

import java.util.HashSet;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/12
 * @Version
 * @Since
 * @Description
 */
public class LifecycleUtils {
    private static final LifecycleUtils ourInstance = new LifecycleUtils();

    public static LifecycleUtils getInstance() {
        return ourInstance;
    }

    private LifecycleUtils() {
    }

    public static void bindLifecycle(Lifecycle lifecycle, LifecycleObserver lifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver);
        //LifecycleUtils.getInstance().add();
    }

    public static void bindLifecycle1(Lifecycle lifecycle) {
        bindLifecycle(lifecycle,new LifecycleObserver1());
    }
    public static void bindLifecycle2(Lifecycle lifecycle) {
        bindLifecycle(lifecycle,new LifecycleObserver2());
    }
}
