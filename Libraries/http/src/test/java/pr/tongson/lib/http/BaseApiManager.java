package pr.tongson.lib.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/28
 * @Version
 * @Since
 * @Description
 */
public class BaseApiManager {
    private static final BaseApiManager ourInstance = new BaseApiManager();

    public static BaseApiManager getInstance() {
        return ourInstance;
    }

    private BaseApiManager() {
    }

    public BaseApiService getApiService(){
        BaseApiService api = getRetrofit().create(BaseApiService.class);
        return api;
    }



    private Retrofit getRetrofit() {
        return setRetrofitBuilder();
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法，
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     */
    private Retrofit setRetrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BaseApiConfig.SERVER_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.addCallAdapterFactory();
        builder.client(getOkHttpClient());
        return builder.build();
    }


    private OkHttpClient getOkHttpClient() {
        return setOkHttpClientBuilder();
    }

    /**
     * 实现重写父类的 setHttpClientBuilder 方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    private OkHttpClient setOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
        //builder.addInterceptor(new HeadInterceptor());
        //添加缓存拦截器 可传入缓存天数，不传默认7天
        //builder.addInterceptor(new CacheInterceptor());
        // 日志拦截器
        //builder.addInterceptor(new LogInterceptor());
        //File file = new File(ACache.PATH_CACHE, "HttpCache");
        //设置缓存配置 缓存最大10M
        //builder.cache(new Cache(file, 10 * 1024 * 1024));
        //添加Cookies自动持久化
        //builder.cookieJar();
        //超时时间 连接、读、写
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(5, TimeUnit.SECONDS);
        return builder.build();
    }

}
