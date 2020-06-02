package pr.tongson.lib.http.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    public abstract void onFailure(Throwable e);

    @Override
    public void onComplete() {

    }
}