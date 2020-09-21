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
public class CenterSmoothScroller extends LinearSmoothScroller {

    public CenterSmoothScroller(Context context) {
        super(context);
    }

    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
    }
}