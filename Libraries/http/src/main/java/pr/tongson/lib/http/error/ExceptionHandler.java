package pr.tongson.lib.http.error;

import retrofit2.HttpException;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description 错误处理器，错误处理引擎
 */
public class ExceptionHandler {

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable responseThrowable;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            responseThrowable = new ResponseThrowable(e, httpException.code());
            responseThrowable.message = ErrorCodeType.getHttpErrorDescriptionByCode(httpException.code());
            return responseThrowable;
        } else if (e instanceof ServerException) {
            ServerException serverException = (ServerException) e;
            responseThrowable = new ResponseThrowable(e, serverException.code);
            responseThrowable.message = serverException.message;
            return responseThrowable;
        } else {
            responseThrowable = new ResponseThrowable(e, ErrorCodeType.LocalError.UNKNOWN.getCode());
            responseThrowable.message = ErrorCodeType.LocalError.UNKNOWN.getDescription();
            return responseThrowable;
        }

    }
}
