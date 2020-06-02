package pr.tongson.lib.http.bean;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/23
 * @Version
 * @Since
 * @Description
 */
public class BaseResponseBean<T> {
    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "BaseResponseBean{" + "data=" + data.toString() + ", errorCode=" + errorCode + ", errorMsg='" + errorMsg + '\'' + '}';
    }
}
