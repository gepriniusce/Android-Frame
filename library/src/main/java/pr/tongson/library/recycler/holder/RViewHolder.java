package pr.tongson.library.recycler.holder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.library.recycler.item.BaseRViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    /**
     * 稀疏的数组
     */
    SparseArray<View> mViews;
    /**
     *
     */
    private View mContentView;

    private BaseRViewItem mViewItem;

    /**
     * 1中ViewHolder对应一种ItemViewType对应一种ViewItem
     */
    private int mItemViewType;

    /**
     * @param parent
     * @param viewItem
     * @return
     */
    public static RViewHolder createViewHolder(ViewGroup parent, BaseRViewItem viewItem) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(viewItem.getItemLayout(), parent, false);
        return new RViewHolder(contentView, viewItem);
    }

    public RViewHolder(@NonNull View contentView, BaseRViewItem viewItem) {
        super(contentView);
        mContentView = contentView;
        this.mViewItem = viewItem;
        mViews = new SparseArray<>();
        viewItem.initView(this);
    }

    /**
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @return
     */
    public View getContentView() {
        return mContentView;
    }

    public BaseRViewItem getViewItem() {
        return mViewItem;
    }
}
