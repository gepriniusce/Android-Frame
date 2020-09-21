package pr.tongson.lib.http.ui.networksetting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pr.tongson.lib.http.R;
import pr.tongson.lib.http.ui.networksetting.NetworkSettingFragment;
import pr.tongson.lib.http.utils.NetworkRealUtils;

public class NetworkSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_setting_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NetworkSettingFragment.newInstance()).commitNow();
        }
        // NetworkRealUtils.isNetworkRealConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetwrokBroadcastReceiver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetwrokBroadcastReceiver.unRegister(this);
    }
}
