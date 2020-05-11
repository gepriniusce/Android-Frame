package pr.tongson.library.jetpack;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/30
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseJetpackDialogFragment extends DialogFragment {
    protected AppCompatActivity mActivity;
    private BaseShareViewModel mSharedViewModel;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = ((BaseJetpackApp) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity).get(BaseShareViewModel.class);
        initViewModel();
    }

    /**
     * 初始化属于自己的ViewModel
     */
    protected abstract void initViewModel();

    /**
     * 给当前BaseFragment用的
     *
     * @return
     */
    protected ViewModelProvider getAppViewModelProvider() {
        return ((BaseJetpackApp) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    /**
     * 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
     *
     * @param fragment
     * @return
     */
    protected ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
        return new ViewModelProvider(fragment, fragment.getDefaultViewModelProviderFactory());
    }

    /**
     * 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
     *
     * @param activity
     * @return
     */
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    /**
     * @param modelClass
     * @param <T>
     * @return
     */
    protected <T extends ViewModel> T getFragmentViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    /**
     * @param modelClass
     * @param <T>
     * @return
     */
    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
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
