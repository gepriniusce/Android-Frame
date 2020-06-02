package pr.tongson.lib.http.base;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import pr.tongson.lib.http.bean.BaseResponseBean;
import pr.tongson.lib.http.environment.IEnvironment;
import pr.tongson.lib.http.error.ErrorCodeType;
import pr.tongson.lib.http.error.HttpErrorHandler;
import pr.tongson.lib.http.error.ServerException;
import pr.tongson.lib.http.interceptor.CommonHeaderInterceptor;
import pr.tongson.lib.http.interceptor.HttpLoggingInterceptor;
import pr.tongson.library.utils.LogUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description
 */
public abstract class NetworkApi implements IEnvironment {
    private static INetworkRequiredInfo mNetworkRequiredInfo;
    private static HashMap<String, Retrofit> mRetrofitHashMap = new HashMap<>();
    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;
    private static boolean mIsFormal = true;

    public NetworkApi() {
        if (!mIsFormal) {
            mBaseUrl = getTestUrl();
        }
        mBaseUrl = getFormalUrl();
    }

    public static void init(INetworkRequiredInfo networkRequiredInfo) {
        mNetworkRequiredInfo = networkRequiredInfo;
        //        mIsFormal=
    }

    private Retrofit getRetrofit(Class service) {
        String key = mBaseUrl + service.getName();
        if (mRetrofitHashMap.get(mBaseUrl) != null) {
            return mRetrofitHashMap.get(key);
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(mBaseUrl);
        retrofitBuilder.client(getOkHttpClient());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        mRetrofitHashMap.put(key, retrofit);
        return retrofit;
    }

    public <T> T getService(Class<T> service) {
        return getRetrofit(service).create(service);
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (getInterceptor() != null) {
                builder.addInterceptor(getInterceptor());
            }
            //添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            //builder.addInterceptor(getCommonHeaderInterceptor());
            //builder.addInterceptor(new CommonRequestInterceptor());
            // 日志拦截器
            if (mNetworkRequiredInfo != null && mNetworkRequiredInfo.isDebug()) {
                //                HttpLoggingInterceptor okHttpLoggingInterceptor = new HttpLoggingInterceptor();
                //                okHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //                builder.addInterceptor(okHttpLoggingInterceptor);
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("OKHTTP");
                httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
                httpLoggingInterceptor.setColorLevel(Level.INFO);
//                builder.addInterceptor(httpLoggingInterceptor);
            }
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            //builder.addInterceptor(new CacheInterceptor());

            //File file = new File(ACache.PATH_CACHE, "HttpCache");
            //设置缓存配置 缓存最大10M
            //builder.cache(new Cache(file, 10 * 1024 * 1024));
            //添加Cookies自动持久化
            //builder.cookieJar();
            //超时时间 连接、读、写
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(5, TimeUnit.SECONDS);
            builder.writeTimeout(5, TimeUnit.SECONDS);
            mOkHttpClient = builder.build();
        }
        return mOkHttpClient;
    }

    private Interceptor getCommonHeaderInterceptor() {
        return new CommonHeaderInterceptor.
                Builder().
                addHeaderParams("", mNetworkRequiredInfo.getAppVersionCode()).
                addHeaderParams("", mNetworkRequiredInfo.getAppVersionName()).
                addHeaderParams("os", "Android").
                build();
    }

    public <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return upstream -> {
            Observable<T> observable = upstream.
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    map(getCommonResponseErrorHandler()).
                    map(getResponseErrorHandler()).
                    onErrorResumeNext(new HttpErrorHandler<T>());
            observable.subscribe(observer);
            return observable;
        };
    }

    protected <T> Function<T, T> getCommonResponseErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                LogUtils.i("Tongson t:" + t);
                if (t == null) {
                    throw new ServerException(ErrorCodeType.LocalError.EMPTY_BEAN);
                }
                if (t instanceof BaseResponseBean) {
                    if (((BaseResponseBean) t).getErrorCode() != ErrorCodeType.LocalError.SUCCESS.getCode()) {
                        throw new ServerException(((BaseResponseBean) t).
                                getErrorCode(), ((BaseResponseBean) t).getErrorMsg());
                    }
                }
                return t;
            }
        };
    }

    protected abstract Interceptor getInterceptor();

    protected abstract <T> Function<T, T> getResponseErrorHandler();


}
