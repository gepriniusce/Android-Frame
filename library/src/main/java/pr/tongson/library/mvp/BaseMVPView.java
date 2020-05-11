package pr.tongson.library.mvp;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface BaseMVPView {

    /**
     * new Presenter
     */
    void setupPresenter();

    /**
     * Use night mode
     *
     * @param isNightMode if is night mode
     */
    void useNightMode(boolean isNightMode);

    //    /**
    //     * showNormal
    //     */
    //    void showNormal();
    //
    //    /**
    //     * Show error
    //     */
    //    void showError();
    //
    //    /**
    //     * Show loading
    //     */
    //    void showLoading();
    //
    //    /**
    //     * Reload
    //     */
    //    void reload();

    //    /**
    //     * Show login view
    //     */
    //    void showLoginView();
    //
    //    /**
    //     * Show logout view
    //     */
    //    void showLogoutView();
    //
    //    /**
    //     * Show collect success
    //     */
    //    void showCollectSuccess();
    //
    //    /**
    //     * Show cancel collect success
    //     */
    //    void showCancelCollectSuccess();

    /**
     * Show toast
     *
     * @param message Message
     */
    void showToast(String message);

    /**
     * Show snackBar
     *
     * @param message Message
     */
    void showSnackBar(String message);

    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

}
