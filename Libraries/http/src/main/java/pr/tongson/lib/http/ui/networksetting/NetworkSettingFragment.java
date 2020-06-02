package pr.tongson.lib.http.ui.networksetting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import pr.tongson.lib.http.R;
import pr.tongson.lib.http.appuse.ArticleBean;
import pr.tongson.lib.http.appuse.ArticleViewModel;
import pr.tongson.lib.http.appuse.IAPI;
import pr.tongson.lib.http.appuse.MyNetworkApi;
import pr.tongson.lib.http.base.INetworkRequiredInfo;
import pr.tongson.lib.http.base.NetworkApi;
import pr.tongson.lib.http.bean.BaseResponseBean;
import pr.tongson.lib.http.observer.BaseObserver;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMFragment;
import pr.tongson.library.mvvm.BaseMVVMViewModel;
import pr.tongson.library.utils.LogUtils;

public class NetworkSettingFragment extends BaseMVVMFragment {

    private ViewModel mViewModel;
    private ArticleViewModel mArticleViewModel;

    private BaseDataBindingConfig mDataBindingConfig;

    public static NetworkSettingFragment newInstance() {
        return new NetworkSettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkApi.init(new INetworkRequiredInfo() {
            @Override
            public boolean isDebug() {
                return true;
            }

            @Override
            public String getAppVersionName() {
                return null;
            }

            @Override
            public String getAppVersionCode() {
                return null;
            }
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = new BaseMVVMViewModel();
        mArticleViewModel=new ArticleViewModel();
    }

    @Override
    protected BaseDataBindingConfig getDataBindingConfig() {
        mDataBindingConfig = new BaseDataBindingConfig(R.layout.network_setting_fragment, mViewModel);
        return mDataBindingConfig;
    }
}

