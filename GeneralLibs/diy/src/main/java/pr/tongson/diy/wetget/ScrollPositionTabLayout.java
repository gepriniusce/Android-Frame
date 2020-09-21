package pr.tongson.diy.wetget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.tabs.TabLayout;


/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/6/27
 * @Version V1.0.0
 * @Since 2020/6/27
 * @Description 监听滚动到的位置的TabLayout
 */
public class ScrollPositionTabLayout extends TabLayout {

    private OnScrollStopListener mOnScrollStopListener;
    private Runnable mScrollerTask;
    private int initPosition;
    private int checkTaskDelayMillis = 100;
    private int childWidth = 0;

    public interface OnScrollStopListener {
        /**
         * scroll have stopped
         */
        void onScrollStopped();

        /**
         * scroll have stopped, and is at left edge
         */
        void onScrollToLeftEdge();

        /**
         * scroll have stopped, and is at right edge
         */
        void onScrollToRightEdge();

        /**
         * scroll have stopped, and is at middle
         */
        void onScrollToMiddle();

        /**
         * scroll have no stopped, and is at middle
         */
        void onScrolling();
    }

    public ScrollPositionTabLayout(Context context) {
        this(context, null);
    }

    public ScrollPositionTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollPositionTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollerTask = new Runnable() {
            @Override
            public void run() {
                int newPosition = getScrollX();
                //                if(newPosition>0){
                //                    if (BuildConfig.DEBUG) {
                //                        Log.d("ScrollPositionTabLayout", "newPosition>0");
                //                    }
                //                }
                if (initPosition - newPosition == 0) {
                    if (mOnScrollStopListener == null) {
                        return;
                    }
                    mOnScrollStopListener.onScrollStopped();
                    Rect outRect = new Rect();
                    getDrawingRect(outRect);

                    //                    LogUtil.d("getScrollX:" + getScrollX());
                    //                    LogUtil.d("outRect.right:" + outRect.right);
                    //                    LogUtil.d("childWidth:" + childWidth);

                    if (getScrollX() == 0) {
                        mOnScrollStopListener.onScrollToLeftEdge();
                    } else if (childWidth + getPaddingLeft() + getPaddingRight() == outRect.right) {
                        mOnScrollStopListener.onScrollToRightEdge();
                    } else {
                        mOnScrollStopListener.onScrollToMiddle();
                    }
                } else {
                    initPosition = getScrollX();
                    postDelayed(mScrollerTask, checkTaskDelayMillis);
                }
            }
        };
    }

    public void setOnScrollStopListener(OnScrollStopListener listener) {
        mOnScrollStopListener = listener;
    }


    public void startScrollerTask() {
        initPosition = getScrollX();
        postDelayed(mScrollerTask, checkTaskDelayMillis);
        childWidth = 0;
        checkTotalWidth();
    }

    private void checkTotalWidth() {
        //        if (childWidth > 0) {
        //            return;
        //        }
        //        LogUtil.d("getChildCount:" + getChildCount());
        for (int i = 0; i < getChildCount(); i++) {
            childWidth += getChildAt(i).getWidth();
        }
        //        LogUtil.d("childWidth:"+childWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mOnScrollStopListener != null) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                mOnScrollStopListener.onScrolling();
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                startScrollerTask();
            }
        }
        return super.onTouchEvent(ev);
    }

    public void scrollToLeft() {
        scrollTo(0, 0);
    }

    public void scrollToRight() {
        childWidth = 0;
        checkTotalWidth();
        scrollTo(childWidth, 0);
    }
}