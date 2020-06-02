package pr.tongson.lib.http.appuse;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import pr.tongson.lib.http.base.NetworkApi;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description wanandroid域名
 */
public class MyNetworkApi extends NetworkApi {
    private static final MyNetworkApi ourInstance = new MyNetworkApi();

    public static MyNetworkApi getInstance() {
        return ourInstance;
    }

    private MyNetworkApi() {

    }

    /**
     *
     * @return
     */
    @Override
    protected Interceptor getInterceptor() {
        return null;
    }

    //        public static <T> Function<T, T> getHttpErrorHandler() {
    //            return response -> {
    //                // if(response instanceof )
    //                if (response instanceof BaseResponse) {
    //                    ExceptionHandler.ServerException e = new ExceptionHandler.ServerException();
    //                    throw e;
    //                }
    //                return response;
    //            };
    //        }

    /**
     * 配置一场抛出
     *
     * @param <T>
     * @return
     */
    @Override
    protected <T> Function<T, T> getResponseErrorHandler() {
        return t -> t;
    }

    @Override
    public String getFormalUrl() {
        return "https://wanandroid.com/";
    }

    @Override
    public String getTestUrl() {
        return "https://wanandroid.com/";
    }
}
