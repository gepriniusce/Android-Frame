package pr.tongson.lib.http.model;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/24
 * @Version
 * @Since
 * @Description
 */
public interface IViewModelResult<T> {

    void onSuccess(T t, boolean isFromCache);

    void onFailure(Throwable e);
}
