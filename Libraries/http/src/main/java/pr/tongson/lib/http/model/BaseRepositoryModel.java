package pr.tongson.lib.http.model;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @param <F> BaseResponseBean<T>
 * @param <T> Bean
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description 仓库Model->数据加工工厂
 * 45
 * <p>
 * 预加载机制
 * 懒加载机制
 * 分页机制
 *
 * network
 * 分级缓存机制
 * 数据库
 *
 * 生命周期机制
 * 一对多ViewModel
 */
public abstract class BaseRepositoryModel<F, T> implements IPage {

    private int mPageNum = 1;

    private boolean isUseCache;
    private boolean isLazyLoad;

    protected ReferenceQueue<IViewModelResult> mViewModelsQueue;
    protected ConcurrentLinkedQueue<WeakReference<IViewModelResult>> mWeakViewModels;

    public BaseRepositoryModel() {
        mViewModelsQueue = new ReferenceQueue<>();
        mWeakViewModels = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void onRefresh() {
        if (isNeedPage()) {
            mPageNum = 1;
        }
        request();
    }

    public boolean isNeedPage() {
        return false;
    }

    @Override
    public void onLoadNextPage(PagingResult pagingResult) {
        if (pagingResult == null) {
            return;
        }
        if (!pagingResult.hasNextPage) {
            return;
        }
        mPageNum++;
        request();
    }

    /**
     * NetWork请求，子类实现
     */
    public abstract void request();

    /**
     * 缓存，子类实现
     */
    public abstract void cache();

    /**
     * 数据库，子类实现
     */
    public abstract void DB();

    /**
     * @param viewModel
     */
    public void registerViewModel(IViewModelResult viewModel) {
        if (viewModel == null) {
            return;
        }
        synchronized (this) {
            Reference<? extends IViewModelResult> reference;
            while ((reference = mViewModelsQueue.poll()) != null) {
                mWeakViewModels.remove(reference);
            }

            for (WeakReference<IViewModelResult> weakViewModel : mWeakViewModels) {
                IViewModelResult vm = weakViewModel.get();
                if (vm == viewModel) {
                    return;
                }
            }
            mWeakViewModels.add(new WeakReference<>(viewModel, mViewModelsQueue));
        }
    }

    public void unRegisterViewModel(IViewModelResult viewModel) {
        if (viewModel == null) {
            return;
        }
        synchronized (this) {
            for (WeakReference<IViewModelResult> weakViewModel : mWeakViewModels) {
                IViewModelResult vm = weakViewModel.get();
                if (vm == viewModel) {
                    mWeakViewModels.remove(vm);
                    break;
                }
            }
        }
    }


    public void clear() {

    }

    public void cancel() {

    }

    public void notifyUI() {

    }

    /**
     * 把原料放入工厂
     *
     * @param data
     */
    public void saveData(F data) {

    }

    /**
     * 从工厂中获取数据
     */
    public T getData() {
        return null;
    }
}
