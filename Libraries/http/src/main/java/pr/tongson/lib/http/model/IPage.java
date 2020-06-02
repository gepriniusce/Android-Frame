package pr.tongson.lib.http.model;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description
 */
public interface IPage {
    void onRefresh();

    void onLoadNextPage(PagingResult pagingResult);

}
