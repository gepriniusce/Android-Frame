package pr.tongson.library.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import pr.tongson.library.mvc.BaseMVCDialogFragment;
import pr.tongson.library.utils.ToastUtils;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseMVPFragment<T extends BaseMVPPresenter> extends BaseMVCDialogFragment implements BaseMVPView {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }


    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showToast(String message) {
        if (getActivity() == null) {
            return;
        }
        ToastUtils.showMessage(getActivity(), message);
    }

    @Override
    public void showSnackBar(String message) {
        if (getActivity() == null) {
            return;
        }
        ToastUtils.showSnackMessage(getActivity(), message);
    }


    @Override
    public void showErrorMsg(String errorMsg) {
        if (getActivity() == null||!isAdded()) {
            return;
        }
        ToastUtils.showSnackMessage(getActivity(), errorMsg);
    }
}
