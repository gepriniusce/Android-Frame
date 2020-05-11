package pr.tongson.library.mvp;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface BaseMVPContract {

    //Demo
    interface View extends BaseMVPView {

    }

    interface Presenter extends BaseMVPPresenter<View> {


    }
}
