package pr.tongson.lib.http.utils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/20
 * @Version
 * @Since
 * @Description
 */
public class PageResult {
    public boolean isFirstPage;
    public boolean isEmpty;
    public boolean hasNextPage;

    public PageResult(boolean isFirstPage, boolean isEmpty, boolean hasNextPage) {
        this.isFirstPage = isFirstPage;
        this.isEmpty = isEmpty;
        this.hasNextPage = hasNextPage;
    }
}
