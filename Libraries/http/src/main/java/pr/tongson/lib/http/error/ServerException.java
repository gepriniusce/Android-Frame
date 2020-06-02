package pr.tongson.lib.http.error;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/23
 * @Version
 * @Since
 * @Description
 */
public class ServerException extends Exception {
    public int code;
    public String message;

    public ServerException(IErrorCodeType error){
        super(error.getDescription());
        this.code=error.getCode();
        this.message=error.getDescription();
    }

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
