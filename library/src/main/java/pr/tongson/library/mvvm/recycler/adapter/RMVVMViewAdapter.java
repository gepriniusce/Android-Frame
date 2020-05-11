package pr.tongson.library.mvvm.recycler.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.library.mvvm.recycler.holder.RMVVMViewHolder;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.library.mvvm.recycler.manager.RMVVMViewItemManager;
import pr.tongson.library.recycler.listener.IOnItemListener;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description
 */
public class RMVVMViewAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<RMVVMViewHolder> {

    RMVVMViewItemManager<T, B> mItemStyles;
    IOnItemListener<T> mOnItemListener;
    List<T> mTList;

    public RMVVMViewAdapter(List<T> TList) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RMVVMViewItemManager<>();
    }

    public RMVVMViewAdapter(List<T> TList, BaseRMVVMViewItem<T, B>... items) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RMVVMViewItemManager<>();
        for (BaseRMVVMViewItem item : items) {
            addItemStyle(item);
        }
    }

    public void addItemStyle(BaseRMVVMViewItem<T, B> item) {
        mItemStyles.addStyle(item);
    }

    @NonNull
    @Override
    public RMVVMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseRMVVMViewItem item = mItemStyles.getRViewItem(viewType);
        RMVVMViewHolder holder = RMVVMViewHolder.createViewHolder(parent, item);
        if (item.openClick()) {
            setListener(holder);
        }
        return holder;
    }

    private void setListener(final RMVVMViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnItemListener.onItemClick(v, mTList.get(position), position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemListener.onItemLongClick(v, mTList.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RMVVMViewHolder holder, int position) {
        final T item = mTList.get(position);
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, item, holder);
        if (binding != null) {
            //当可变或可观察对象发生更改时，绑定会按照计划在下一帧之前发生更改。但有时必须立即执行绑定。要强制执行，请使用 executePendingBindings() 方法。
            binding.executePendingBindings();
        }
    }

    private void onBindItem(B binding, T t, RMVVMViewHolder holder) {
        BaseRMVVMViewItem<T, B> baseRViewItem = holder.getViewItem();
        baseRViewItem.onBindViewHolder((B) binding, (T) t, holder);
    }

    @Override
    public int getItemCount() {
        return mTList == null ? 0 : mTList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiStyle()) {
            return mItemStyles.getItemViewType(mTList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    private boolean hasMultiStyle() {
        return mItemStyles.getCount() > 0;
    }

    public void setItemListener(IOnItemListener<T> itemListener) {
        mOnItemListener = itemListener;
    }

}
