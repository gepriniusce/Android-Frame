package pr.tongson.library.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 类名      PackageUtils.java</br>
 * 创建日期 2012-11-21</br>
 * 作者  LiWenLong</br>
 * Email lendylongli@gmail.com</br>
 * 更新时间  下午6:47:17</br>
 * 最后更新者 LeOn</br>
 * 说明   应用信息工具类</br>
 */
public class PackageUtils {

    public static final String PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
    public static final String PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";

    public static final String DATA_SCHEME = "package";

    /**
     * 获取所有应用信息
     *
     * @param context 上下文
     */
    public static List<ResolveInfo> getAllResolveInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> allapps = manager.queryIntentActivities(mainIntent, 0);
        return allapps;
    }

    /**
     * 获取当前设备所安装的所有应用信息
     *
     * @param context 上下文
     */
    public static List<PackageInfo> getAllPackageInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        return manager.getInstalledPackages(0);
    }

    /**
     * 获取当前应用的信息
     *
     * @param context 上下文
     * @return 返回{@link PackageInfo}
     */
    public static PackageInfo getPackageInfo(Context context) {
        return getPackageInfo(context, context.getPackageName());
    }

    /**
     * 获取应用的信息
     *
     * @param context     上下文
     * @param packagename 包名
     * @return 返回{@link PackageInfo}
     */

    public static PackageInfo getPackageInfo(Context context, String packagename) {
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (NameNotFoundException e) {
        }
        return pi;
    }

    /**
     * 打开应用
     *
     * @param context     上下文
     * @param packagename 包名
     */
    public static boolean openApplication(Context context, String packagename) {
        try {
            Intent intent = getAppLaunchIntent(context, packagename);
            context.getApplicationContext().startActivity(intent);
        } catch (Exception e) {
            L.d(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 获取应用的启动Intent
     *
     * @param context     上下文
     * @param packagename 要启动的应用包名
     */
    public static Intent getAppLaunchIntent(Context context, String packagename) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packagename);
        if (intent == null) {
            return null;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 安装应用
     *
     * @param context 上下文
     * @param file    apk文件
     */
    public static void installApplication(Context context, File file) {
        Intent intent = getInstallApplicationIntent(context, file);
        context.startActivity(intent);
    }

    /**
     * 获取要安装应用的Intent
     *
     * @param context 上下文
     * @param file    apk的文件
     */
    public static Intent getInstallApplicationIntent(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 获取一个应用信息
     *
     * @param context     上下文
     * @param packagename 包名
     */
    public static ResolveInfo getResolveInfo(Context context, String packagename) {
        List<ResolveInfo> allapps = getAllResolveInfo(context);
        Iterator<ResolveInfo> iterator = allapps.iterator();
        while (iterator.hasNext()) {
            ResolveInfo info = iterator.next();
            String pak = info.activityInfo.packageName;
            if (pak.equals(packagename)) {
                return info;
            }
        }
        return null;
    }

    /**
     * 获取当前应用的版本名
     *
     * @param context 上下文
     */
    public static String getVersionName(Context context) {
        PackageInfo info = getPackageInfo(context);
        if (info == null) {
            return "Unknown";
        }
        return info.versionName;
    }

    /**
     * 获取当前应用的版本号
     *
     * @param context 上下文
     */
    public static int getVersionCode(Context context) {
        PackageInfo info = getPackageInfo(context);
        if (info == null) {
            return -1;
        }
        return info.versionCode;
    }

    /**
     * 获取当前应用的名称
     *
     * @param context 上下文
     */
    public static String getAppName(Context context) {
        PackageInfo info = PackageUtils.getPackageInfo(context);
        PackageManager pm = context.getPackageManager();
        String nameName = pm.getApplicationLabel(info.applicationInfo).toString();
        return nameName;
    }

    /**
     * 获取自身应用的icon
     *
     * @param context 上下文
     */
    public static Drawable getApplicationIcon(Context context) {
        return context.getApplicationInfo().loadIcon(context.getPackageManager());
    }

    /**
     * 获取自身应用的icon
     *
     * @param context 上下文
     * @param pkg     应用包名
     */
    public static Drawable getApplicationIcon(Context context, String pkg) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getApplicationIcon(pkg);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
