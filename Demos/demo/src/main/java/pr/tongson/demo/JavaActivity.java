package pr.tongson.demo;

import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.library.handler.WeakHandler;
import pr.tongson.library.utils.LogUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

public class JavaActivity extends AppCompatActivity {
    // private WeakHandler<JavaActivity> mHandler = new WeakHandler<>(this);

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("Tongson isFinishing():" + isFinishing());
//                if (!isFinishing()) {
                    log();
//                }
            }
        }, 2000);
        LogUtils.i("Tongson onCreate");
    }

    private void log() {
        LogUtils.i("Tongson:" + this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.i("Tongson onBackPressed");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("Tongson onDestroy");

        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
            LogUtils.i("Tongson removeCallbacksAndMessages:" + mHandler);
        }
    }
}