package pr.tongson.diy.wetget;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/7/3
 * @Version
 * @Since
 * @Description
 */
public class SmoothScroller extends LinearSmoothScroller {
    public SmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }
}