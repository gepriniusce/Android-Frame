package pr.tongson.lib.http;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/28
 * @Version
 * @Since
 * @Description
 */
public interface BaseApiService {


    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    Call<ResponseBody> getAritrilList(@Path("page") int pageNo);

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    Call<TestResultBean> getAritrilListGson(@Path("page") int pageNo);



}
