package pr.tongson.lib.http.ui.networksetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import pr.tongson.library.utils.LogUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/6/10
 * @Version
 * @Since
 * @Description
 */
public class NetwrokBroadcastReceiver extends BroadcastReceiver {

    private static final NetwrokBroadcastReceiver ourInstance = new NetwrokBroadcastReceiver();

    static NetwrokBroadcastReceiver getInstance() {
        return ourInstance;
    }

    private NetwrokBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        LogUtils.i("NetwrokBroadcastReceiver onReceive action:" + intent.getAction());
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
    }


    public static void register(@NonNull Context context) {
        // 实例化IntentFilter
        IntentFilter intentFilter = new IntentFilter();
        //设置接收广播的类型
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        // 调用Context的registerReceiver()方法进行动态注册
        context.registerReceiver(NetwrokBroadcastReceiver.getInstance(), intentFilter);
    }

    /**
     * 注册广播后，要在相应位置记得销毁广播
     * 即在onPause() 中unregisterReceiver(mBroadcastReceiver)
     * 当此Activity实例化时，会动态将MyBroadcastReceiver注册到系统中
     * 当此Activity销毁时，动态注册的MyBroadcastReceiver将不再接收到相应的广播。
     */
    public static void unRegister(@NonNull Context context) {
        // 销毁在onResume()方法中的广播
        context.unregisterReceiver(NetwrokBroadcastReceiver.getInstance());
    }

    public interface Callback{

    }

    private class pollingPingThread extends Thread{

        private Callback mCallback;

        @Override
        public void run() {
            super.run();
        }
    }
}
