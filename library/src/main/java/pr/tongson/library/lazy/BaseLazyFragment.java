package pr.tongson.library.lazy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseLazyFragment extends Fragment {

    private Object lock;
    protected View rootView;
    protected boolean isVisible = true;
    protected boolean isPrepared = false;
    protected boolean isLoaded = false;
    protected boolean isInitViewed = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lock = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            inflateView(inflater, container);
            initView();
            loadData();
        }
        ViewGroup group = (ViewGroup) rootView.getParent();
        if (group != null) {
            group.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //只要走过这个方法就认为已经加载了根View了
        isPrepared = true;
        visLoadLazy();
    }

    /**
     * @return 获取layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view，只加载一次。
     */
    protected abstract void initView();

    /**
     * 初始化数据，只加载一次。
     */
    protected abstract void loadData();

    /**
     * 懒加载的时候初始化view，只加载一次。
     */
    protected abstract void initViewLazy();

    /**
     * 懒加载的时候初始化data，只加载一次。
     */
    protected abstract void loadDataLazy();

    /**
     * 懒加载调用的方法
     */
    private void visLoadLazy() {
        synchronized (lock) {
            //如果可见说明这个Fragment初次加载就是可见，应立即初始化布局
            if (isVisible) {
                //只有没有加载才去初始化View
                if (!isInitViewed) {
                    initViewLazy();
                    isInitViewed = true;
                }
                //只有没有加载才去绑定数据
                if (!isLoaded) {
                    loadDataLazy();
                    isLoaded = true;
                }
            }
            lock.notify();
        }
    }

    /**
     * 加载数据的方法
     */
    private void onLoadedData() {
        if (!isPrepared) {
            return;
        }
        visLoadLazy();
    }

    /**
     * 不提供覆写，需监听可见性的子类可覆写{@link #onFragmentVisible()}和{@link #onFragmentInvisible()}方法
     *
     * @param isVisibleToUser 当前Fragment的可见性，onCreateView之前调用
     */
    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (getUserVisibleHint()) {
            onLoadedData();
            if (isPrepared) {
                onFragmentVisible();
            }
        } else {
            if (isPrepared) {
                onFragmentInvisible();
            }
        }
    }

    private void inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(getLayoutId(), container, false);
        rootView = view;
    }

    /**
     * fragment显示,多次加载,因为在onCreateView之前调用，所以viewpager中第一次加载的时候不显示
     */
    protected abstract void onFragmentVisible();

    /**
     * fragment隐藏,多次加载
     */
    protected abstract void onFragmentInvisible();

    @Nullable
    protected final <T extends View> T findViewById(@IdRes int id) {
        if (rootView == null) {
            return null;
        }
        return rootView.findViewById(id);
    }
}
