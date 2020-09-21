package pr.tongson.lib.http.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import androidx.annotation.NonNull;
import pr.tongson.library.utils.AppUtils;
import pr.tongson.library.utils.LogUtils;
import pr.tongson.library.utils.NetworkUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/6/11
 * @Version
 * @Since
 * @Description
 */
public class NetworkRealUtils {
    public static boolean mIsNetworkAvailable;
    private static Object monitor = new Object();
    private static NetworkStateThread mNetworkStateThread = null;

    private NetworkRealUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static synchronized boolean isNetworkRealConnected(NetworkStateThread.NetworkStateCallBack callBack) {
        if (mNetworkStateThread == null) {
            mNetworkStateThread = new NetworkStateThread(callBack);
            mNetworkStateThread.start();
            mIsNetworkAvailable = NetworkUtils.isConnected();
            LogUtils.i("isNetworkRealConnected=>" + "mIsNetworkAvailable:" + mIsNetworkAvailable);
        }
        return mIsNetworkAvailable;
    }

    private static class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            LogUtils.i("NetworkChangeReceiver onReceive action:" + intent.getAction());
            // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            }
        }
    }

    public static void register(@NonNull Context context) {
        // 实例化NetworkChangeReceiver
        NetworkChangeReceiver receiver = new NetworkChangeReceiver();
        // 实例化IntentFilter
        IntentFilter intentFilter = new IntentFilter();
        //设置接收广播的类型
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        // 调用Context的registerReceiver()方法进行动态注册
        context.registerReceiver(receiver, intentFilter);
    }

    public static class NetworkStateThread extends Thread {
        public interface NetworkStateCallBack {
            void afterPingIp();
        }

        private NetworkStateCallBack mNetworkStateCallBack;

        public NetworkStateThread(NetworkStateCallBack networkStateCallBack) {
            mNetworkStateCallBack = networkStateCallBack;
        }

        @Override
        public void run() {
            super.run();
            register(AppUtils.getApp());
            while (true) {
                pingIPs();
                if (mNetworkStateCallBack != null) {
                    mNetworkStateCallBack.afterPingIp();
                }
                synchronized (monitor) {
                    try {
                        monitor.wait(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private static int indexIP = 0;
    private static final String[] dnsAddress = {"114.114.114.114", "119.29.29.29", "223.5.5.5", "114.114.115.115", "223.6.6.6", "182.254.116.116", "180.76.76.76"};

    private static void pingIPs() {
        int maxIndex = dnsAddress.length;
        int i = indexIP;

        boolean result = isOnline(i);
        if (!result) {
            final int k = i + 1;
            for (i = k; i < k + 3; i++) {
                result = isOnline(i % maxIndex);
                if (result) {
                    break;
                }
            }
        }
        mIsNetworkAvailable = result;
        LogUtils.i("pingIPs=>" + "mIsNetworkAvailable:" + mIsNetworkAvailable + ";i:" + i + ";maxIndex:" + maxIndex);
        indexIP = i % maxIndex;
    }

    private static boolean isOnline(int index) {
        String ip = dnsAddress[index];
        return tcp2DNSServer(ip);
    }

    private static boolean tcp2DNSServer(String dnsServerIP) {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(dnsServerIP, 53);
            sock.connect(socketAddress, timeoutMs);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
