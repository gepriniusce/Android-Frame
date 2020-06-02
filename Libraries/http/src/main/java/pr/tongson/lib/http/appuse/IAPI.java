package pr.tongson.lib.http.appuse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import pr.tongson.lib.http.bean.BaseResponseBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/15
 * @Version
 * @Since
 * @Description
 */
public interface IAPI {
    /**
     * 获取首页文章数据
     *
     * @param pageNo
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponseBean<ArticleBean>> getArticleListRxGeneric(@Path("page") int pageNo);
}
