package pr.tongson.library.mvvm.recycler.holder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.library.utils.LogUtils;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description 1中ViewHolder对应一种ItemViewType对应一种ViewItem
 * private int mItemViewType;
 */
public class RMVVMViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private B mBinding;

    /**
     * 该ViewHolder所对应的ViewItem
     */
    private BaseRMVVMViewItem mViewItem;

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewItem
     * @return
     */
    public static <B extends ViewDataBinding> RMVVMViewHolder createViewHolder(@NonNull ViewGroup parent, BaseRMVVMViewItem viewItem) {
        BaseDataBindingConfig dataBindingConfig = viewItem.getDataBindingConfig();
        B binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewItem.getItemLayout(), parent, false);
        if (parent.getContext() instanceof LifecycleOwner) {
            LogUtils.i("Tongson setLifecycleOwner");
            binding.setLifecycleOwner((LifecycleOwner) parent.getContext());
        }
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        return new RMVVMViewHolder(binding.getRoot(), viewItem, binding);
    }

    public RMVVMViewHolder(@NonNull View contentView, BaseRMVVMViewItem viewItem, B binding) {
        super(contentView);
        this.mViewItem = viewItem;
        viewItem.initView(this);
        mBinding = binding;
    }

    public BaseRMVVMViewItem getViewItem() {
        return mViewItem;
    }

}
