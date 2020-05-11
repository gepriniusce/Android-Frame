package pr.tongson.library.mvp;

import io.reactivex.disposables.Disposable;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface BaseMVPPresenter<T extends BaseMVPView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

    /**
     * Get night mode state
     *
     * @return if is night mode
     */
    boolean getNightModeState();

//    /**
//     * Set login status
//     *
//     * @param loginStatus login status
//     */
//    void setLoginStatus(boolean loginStatus);
//
//    /**
//     * Get login status
//     *
//     * @return if is login status
//     */
//    boolean getLoginStatus();
//
//    /**
//     * Get login account
//     *
//     * @return login account
//     */
//    String getLoginAccount();
//
//    /**
//     * Set login status
//     *
//     * @param account account
//     */
//    void setLoginAccount(String account);
//
//    /**
//     * Set login password
//     *
//     * @param password password
//     */
//    void setLoginPassword(String password);

//    /**
//     * Get current page
//     *
//     * @return current page
//     */
//    int getCurrentPage();

}
