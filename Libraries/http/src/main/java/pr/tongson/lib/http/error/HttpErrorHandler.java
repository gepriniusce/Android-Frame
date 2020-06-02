package pr.tongson.lib.http.error;


import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description Rx
 */
public class HttpErrorHandler<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) {
        // ExceptionHandler为处理异常的驱动器throwable
        throwable.printStackTrace();
        return Observable.error(ExceptionHandler.handleException(throwable));
    }
}
