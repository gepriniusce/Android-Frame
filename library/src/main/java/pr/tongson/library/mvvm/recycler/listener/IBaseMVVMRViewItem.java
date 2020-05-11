package pr.tongson.library.mvvm.recycler.listener;


import androidx.databinding.ViewDataBinding;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.recycler.holder.RMVVMViewHolder;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description 抽象的itemView
 */
public interface IBaseMVVMRViewItem<T, B extends ViewDataBinding> {

    /**
     * DataBinding配置
     *
     * @return
     */
    BaseDataBindingConfig getDataBindingConfig();

    /**
     * @return
     */
    int getItemLayout();

    void initView(RMVVMViewHolder holder);

    /**
     * @return
     */
    boolean openClick();

    /**
     * getItemViewType的时候调用
     *
     * @param entity
     * @param position
     * @return 必须返回true不然会throw
     */
    boolean isItemView(T entity, int position);


    /**
     * onBindViewHolder的时候调用
     *
     * @param binding
     * @param t
     * @param holder
     */
    void onBindViewHolder(B binding, T t, RMVVMViewHolder holder);
}
