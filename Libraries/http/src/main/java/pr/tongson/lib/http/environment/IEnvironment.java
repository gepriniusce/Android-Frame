package pr.tongson.lib.http.environment;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description
 */
public interface IEnvironment {
    /**
     * @return 正式接口
     */
    String getFormalUrl();

    /**
     * @return 测试接口
     */
    String getTestUrl();
}
