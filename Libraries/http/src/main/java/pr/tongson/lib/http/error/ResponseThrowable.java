package pr.tongson.lib.http.error;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description 通过handleException得到的ResponseThrowable
 */
public class ResponseThrowable extends Throwable {

    public String message;

    /**
     * 添加一个带空参数的异常
     */
    public ResponseThrowable() {
    }

    /**
     * 查看异常源码可知，可以让父类的构造方法来解决异常信息
     *
     * @param message
     */
    public ResponseThrowable(String message) {
        super(message);
    }

    public ResponseThrowable(Throwable e, int httpError) {

    }
}
