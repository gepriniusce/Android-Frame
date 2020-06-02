package pr.tongson.lib.http.bean;

import java.io.Serializable;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/20
 * @Version
 * @Since
 * @Description
 */
public class BaseCacheBean<T> implements Serializable {
    public long updateTimeInMills;
    public T data;
}
