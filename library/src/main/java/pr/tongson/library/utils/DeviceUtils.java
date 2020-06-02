package pr.tongson.library.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/27
 * @Version
 * @Since
 * @Description
 */
public class DeviceUtils {
    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取设备与应用的详细信息
     *
     * @param context 上下文
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static String getDeviceInfo(Context context) {
        StringBuilder builder = new StringBuilder();
        try {
            PackageInfo info = PackageUtils.getPackageInfo(context);
            builder.append("----- 应用程序信息 ------").append("\n");
            builder.append("应用程序包名:").append(info.packageName).append("\n");
            builder.append("版本信息:").append(info.versionName).append("\n");
            builder.append("版本号:").append(info.versionCode).append("\n");
        } catch (Exception e1) {
        }
        try {
            builder.append("\n\n----- 设备信息 ----\n");
            builder.append("DEVICE ").append(Build.DEVICE).append("\n");
            builder.append("ID ").append(Build.ID).append("\n");
            builder.append("MANUFACTURER ").append(Build.MANUFACTURER).append("\n");
            builder.append("MODEL ").append(Build.MODEL).append("\n");
            builder.append("PRODUCT ").append(Build.PRODUCT).append("\n");
            builder.append("VERSION_CODES.BASE ").append(Build.VERSION_CODES.BASE).append("\n");
            builder.append("VERSION.RELEASE ").append(Build.VERSION.RELEASE).append("\n");
            builder.append("SDK").append(Build.VERSION.SDK_INT).append("\n");
        } catch (Exception e) {
        }
        return builder.toString();
    }
}
