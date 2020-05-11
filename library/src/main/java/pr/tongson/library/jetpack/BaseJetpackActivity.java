package pr.tongson.library.jetpack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/30
 * @Version
 * @Since
 * @Description Jetpack
 * ViewModel管理、
 */
public abstract class BaseJetpackActivity extends AppCompatActivity {

    private BaseShareViewModel mSharedViewModel;
    private ViewModelProvider mActivityProvider;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = getAppViewModelProvider().get(BaseShareViewModel.class);
        initViewModel();
    }

    /**
     * 初始化属于自己的ViewModel
     */
    protected abstract void initViewModel();

    /**
     * 使用 Application 共享
     * 给此BaseActivity 得到ViewModelProvider
     * application
     *
     * @return 唯一 ViewModelProvider  ViewModel
     */
    private ViewModelProvider getAppViewModelProvider() {
        return ((BaseJetpackApp) getApplicationContext()).getAppViewModelProvider(this);
    }

    /**
     * 使用 Application 共享
     * 暴露给自己的孩子 得到ViewModelProvider
     *
     * @param activity 子类的 activity
     * @return 唯一  ViewModelProvider  ViewModel
     */
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    /**
     * @param modelClass
     * @param <T>
     * @return
     */
    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    /**
     * @return SharedViewModel
     */
    public BaseShareViewModel getSharedViewModel() {
        return mSharedViewModel;
    }
}
