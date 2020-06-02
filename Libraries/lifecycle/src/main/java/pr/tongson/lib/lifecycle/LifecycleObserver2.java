package pr.tongson.lib.lifecycle;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/12
 * @Version
 * @Since
 * @Description
 */
public class LifecycleObserver2 implements DefaultLifecycleObserver {
    private final String TAG = LifecycleObserver2.class.getSimpleName();

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onDestroy");
    }

}
