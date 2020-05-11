package pr.tongson.library.mvc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * <b>Create Date:</b> 2020/3/28<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseMVCFragment extends Fragment {

    protected View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            inflateView(inflater, container);
            initView();
            onViewCreated();
            setListener();
            processLogic();
        }
        ViewGroup group = (ViewGroup) mRootView.getParent();
        if (group != null) {
            group.removeView(mRootView);
        }
        return mRootView;
    }

    protected void inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mRootView = view;
    }

    /**
     * @return 获取layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView();

    /**
     * 有一些事情是在initView()<-onViewCreated()->processLogic()之间执行的
     * 例如MVP的presenter的初始化
     */
    protected void onViewCreated() {

    }
    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     */
    protected abstract void processLogic();

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        if (mRootView == null) {
            return null;
        }
        return mRootView.findViewById(id);
    }
}
