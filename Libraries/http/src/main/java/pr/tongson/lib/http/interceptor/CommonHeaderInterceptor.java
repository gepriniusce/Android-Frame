package pr.tongson.lib.http.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description 通用请求头
 */
public class CommonHeaderInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();


    //    private INetworkRequiredInfo mRequiredInfo;
    //    public CommonHeaderInterceptor(INetworkRequiredInfo requiredInfo) {
    //        mRequiredInfo = requiredInfo;
    //    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        CommonHeaderInterceptor mCommonHeaderInterceptor;

        public Builder() {
            mCommonHeaderInterceptor = new CommonHeaderInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mCommonHeaderInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public CommonHeaderInterceptor build() {
            return mCommonHeaderInterceptor;
        }
    }
}
