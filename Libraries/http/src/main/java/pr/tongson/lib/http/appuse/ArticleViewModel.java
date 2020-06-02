package pr.tongson.lib.http.appuse;

import pr.tongson.lib.http.bean.BaseResponseBean;
import pr.tongson.lib.http.model.BaseRepositoryModel;
import pr.tongson.lib.http.model.BaseViewModel;
import pr.tongson.lib.http.observer.BaseObserver;
import pr.tongson.library.utils.LogUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/28
 * @Version
 * @Since
 * @Description
 */
public class ArticleViewModel extends BaseViewModel<ArticleViewModel.ArticleModel, BaseResponseBean> {

    public ArticleViewModel() {
        ArticleModel articleModel = new ArticleModel();
        articleModel.registerViewModel(this);
        articleModel.getData();
    }

    @Override
    public void onSuccess(BaseResponseBean baseResponseBean, boolean isFromCache) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    class ArticleModel extends BaseRepositoryModel<BaseResponseBean, ArticleBean> {
        @Override
        public void request() {
            MyNetworkApi.
                    getInstance().
                    getService(IAPI.class).
                    getArticleListRxGeneric(1).
                    compose(MyNetworkApi.
                            getInstance().
                            applySchedulers(new BaseObserver<BaseResponseBean<ArticleBean>>() {
                                @Override
                                public void onSuccess(BaseResponseBean<ArticleBean> resultBean) {
                                    saveData(resultBean);
                                    LogUtils.i("Tongson resultBean:" + resultBean.toString());
                                }

                                @Override
                                public void onFailure(Throwable e) {
                                    LogUtils.i("Tongson t:" + e.getMessage());
                                }
                            }));
        }

        @Override
        public void cache() {

        }

        @Override
        public void DB() {

        }
    }
}
