package pr.tongson.library.mvvm.recycler.manager;

import androidx.collection.SparseArrayCompat;
import androidx.databinding.ViewDataBinding;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description ItemViewType的管理者
 */
public class RMVVMViewItemManager<T,B extends ViewDataBinding> {
    /**
     *
     */
    private SparseArrayCompat<BaseRMVVMViewItem<T, B>> mViewItemStyles = new SparseArrayCompat<>();

    /**
     * @param item
     */
    public void addStyle(BaseRMVVMViewItem<T, B> item) {
        if (item != null) {
            item.setItemViewType(mViewItemStyles.size());
            mViewItemStyles.put(mViewItemStyles.size(), item);
        }
    }

    /**
     * @return
     */
    public int getCount() {
        return mViewItemStyles.size();
    }

    /**
     * @param viewType
     * @return
     */
    public BaseRMVVMViewItem getRViewItem(int viewType) {
        return mViewItemStyles.get(viewType);
    }


    /**
     * 通过entity跟position得到ItemViewType
     *
     * @param entity
     * @param position
     * @return
     */
    public int getItemViewType(T entity, int position) {
        for (int i = mViewItemStyles.size() - 1; i >= 0; i--) {
            BaseRMVVMViewItem<T, B> item = mViewItemStyles.valueAt(i);

            //            if (item.getItemViewType() ==entity/position) {
            //                return mViewItemStyles.keyAt(i);
            //            }

            //position应该与Style一一对应
            if (item.isItemView(entity, position)) {
                return mViewItemStyles.keyAt(i);
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目getItemViewType");
    }
}
