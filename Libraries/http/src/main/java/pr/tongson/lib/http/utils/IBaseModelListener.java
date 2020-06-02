package pr.tongson.lib.http.utils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/20
 * @Version
 * @Since
 * @Description
 */
public interface IBaseModelListener<T> {

    void onLoadFinish(T data, PageResult... pageResult);

    void onLoadFail(String prompt, PageResult... pageResult);
}
