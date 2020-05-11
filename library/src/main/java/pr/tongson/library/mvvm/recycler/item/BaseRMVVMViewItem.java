package pr.tongson.library.mvvm.recycler.item;


import androidx.databinding.ViewDataBinding;
import pr.tongson.library.mvvm.bindingproxy.BaseClickProxy;
import pr.tongson.library.mvvm.recycler.holder.RMVVMViewHolder;
import pr.tongson.library.mvvm.recycler.listener.IBaseMVVMRViewItem;
import pr.tongson.library.utils.L;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseRMVVMViewItem<T, B extends ViewDataBinding> implements IBaseMVVMRViewItem<T, B> {

    private int itemViewType;
    /**
     * 点击事件代理
     */
    private BaseClickProxy mClickProxy;

    /**
     * 无参构造方法
     */
    public BaseRMVVMViewItem() {
    }

    /**
     * 构造自己的事件代理
     *
     * @param clickProxy
     */
    public BaseRMVVMViewItem(BaseClickProxy clickProxy) {
        mClickProxy = clickProxy;
    }

    /**
     * MVVM一般用不到
     *
     * @param holder
     */
    @Override
    public void initView(RMVVMViewHolder holder) {
        L.i("Tongson initView");
    }

    /**
     * 默认：可以点击
     *
     * @return
     */
    @Override
    public boolean openClick() {
        return true;
    }

    /**
     * 默认：单个itemType
     *
     * @param entity
     * @param position
     * @return
     */
    @Override
    public boolean isItemView(T entity, int position) {
        if (entity != null) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemLayout() {
        return getDataBindingConfig().getLayout();
    }


    //////////////////////////////////////////////Setter and Getter

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }

    public BaseClickProxy getClickProxy() {
        return mClickProxy;
    }

    public void setClickProxy(BaseClickProxy clickProxy) {
        mClickProxy = clickProxy;
    }
}
