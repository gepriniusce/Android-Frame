package pr.tongson.library.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/03/18
 * @Version V1.0.0
 * @Since
 * @Description 获取设备屏幕宽高比
 */
public class UIUtils {
    /**
     * volatile关键字禁止指令重排
     */
    private static volatile UIUtils ourInstance;

    /**
     * 标准设备
     */
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;

    /**
     * 实际设备
     */
    public float displayMetricsWidth;
    public float displayMetricsHeight;
    public float systemBarHeight;

    public static UIUtils getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (UIUtils.class) {
                ourInstance = new UIUtils(context);
            }
        }
        return ourInstance;
    }

    public static UIUtils getInstance() {
        if (ourInstance == null) {
            throw new RuntimeException("UIUtils应该先调用含有Context的构造方法进行初始化");
        }
        return ourInstance;

    }

    public UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (this.displayMetricsWidth == 0.0f || this.displayMetricsHeight == 0.0f) {
            //在这里得到设备的真实值
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //状态栏
            systemBarHeight = (float) getSystemBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //横屏
                this.displayMetricsWidth = (float) (displayMetrics.heightPixels);
                this.displayMetricsHeight = (float) (displayMetrics.widthPixels - systemBarHeight);
            } else {
                //竖屏
                this.displayMetricsWidth = (float) (displayMetrics.widthPixels);
                this.displayMetricsHeight = (float) (displayMetrics.heightPixels - systemBarHeight);
            }
        }
    }

    public float getVerticalScaleValue() {
        return ((float) (displayMetricsWidth)) / STANDARD_WIDTH;

    }

    public float getHorizontalScaleValue() {
        return ((float) (displayMetricsHeight)) / STANDARD_HEIGHT;
    }


    private int getSystemBarHeight(Context context) {
        return getValue(context, "com.android.internal.R$dimen", "system_bar_height", 48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
        //com.android.internal.R$dimen   ->  system_bar_height  状态栏的高度
        Class<?> clz = null;
        try {
            clz = Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelOffset(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return defaultValue;
    }


}
