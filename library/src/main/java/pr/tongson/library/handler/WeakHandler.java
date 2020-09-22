package pr.tongson.library.handler;


import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/9/22
 * @Version
 * @Since
 * @Description
 */
public class WeakHandler<T> extends Handler {

    private WeakReference<T> mActivity;

    public WeakHandler(T activity) {
        mActivity = new WeakReference<T>(activity);
    }
}
