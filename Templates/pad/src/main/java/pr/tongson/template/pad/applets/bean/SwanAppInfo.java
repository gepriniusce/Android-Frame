package pr.tongson.template.pad.applets.bean;

import android.graphics.Bitmap;

/**
 * SwanApp info for visit history.
 */
public final class SwanAppInfo {


    /**
     * 小程序的ID
     */
    private String appID;
    /**
     * 小程序的名称
     */
    private String appTitle;
    /**
     * 小程序的logo
     */
    private Bitmap appLogoBitmap;
    /**
     * 小程序的logo url
     */
    private String appLogoUrl;
    /**
     * 小程序的来源
     */
    private String appSource;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public Bitmap getAppLogoBitmap() {
        return appLogoBitmap;
    }

    public void setAppLogoBitmap(Bitmap appLogoBitmap) {
        this.appLogoBitmap = appLogoBitmap;
    }

    public String getAppLogoUrl() {
        return appLogoUrl;
    }

    public void setAppLogoUrl(String appLogoUrl) {
        this.appLogoUrl = appLogoUrl;
    }

    public String getAppSource() {
        return appSource;
    }

    public void setAppSource(String appSource) {
        this.appSource = appSource;
    }
}