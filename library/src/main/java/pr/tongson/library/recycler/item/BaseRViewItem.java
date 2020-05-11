package pr.tongson.library.recycler.item;


import pr.tongson.library.recycler.listener.IBaseRViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseRViewItem<T> implements IBaseRViewItem<T> {

    private int itemViewType;

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }
}
