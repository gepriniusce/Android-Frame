package pr.tongson.lib.http.model;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description
 */
public class PagingResult {
    public boolean isFirstPage;
    public boolean isEmpty;
    public boolean hasNextPage;

    public PagingResult(boolean isFirstPage, boolean isEmpty, boolean hasNextPage) {
        this.isFirstPage = isFirstPage;
        this.isEmpty = isEmpty;
        this.hasNextPage = hasNextPage;
    }
}
