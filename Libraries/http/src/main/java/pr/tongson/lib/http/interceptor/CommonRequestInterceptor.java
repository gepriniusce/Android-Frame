package pr.tongson.lib.http.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import pr.tongson.library.utils.LogUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description
 */
public class CommonRequestInterceptor implements Interceptor {
    private final String TAG = "ResponseInterceptor";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        LogUtils.d(TAG + "==>requestTime=" + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
