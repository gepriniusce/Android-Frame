package pr.tongson.library.recycler.listener;

import android.view.View;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/23
 * @Version
 * @Since
 * @Description
 */
public interface IOnItemListener<T> {
    /**
     * @param view
     * @param entity
     * @param position
     */
    void onItemClick(View view, T entity, int position);

    /**
     * @param view
     * @param entity
     * @param position
     * @return
     */
    boolean onItemLongClick(View view, T entity, int position);
}
