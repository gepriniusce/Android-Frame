package pr.tongson.library.common;

import android.app.Application;

import androidx.lifecycle.ViewModelStore;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/6/11
 * @Version
 * @Since
 * @Description
 */
public class BaseCommonApp extends Application {

    private static BaseCommonApp ourInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    public static BaseCommonApp getInstance() {
        return ourInstance;
    }
}
