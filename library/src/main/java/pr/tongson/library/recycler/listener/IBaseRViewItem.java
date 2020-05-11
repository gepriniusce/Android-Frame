package pr.tongson.library.recycler.listener;


import pr.tongson.library.recycler.holder.RViewHolder;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description 抽象的itemView
 */
public interface IBaseRViewItem<T> {

    /**
     * @return
     */
    int getItemLayout();

    void initView(RViewHolder holder);

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
     * @param holder
     * @param entity
     */
    void convert(RViewHolder holder, T entity);


}
