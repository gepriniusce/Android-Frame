package pr.tongson.lib.http.ui.networksetting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pr.tongson.lib.http.R;
import pr.tongson.lib.http.ui.networksetting.NetworkMonitorFragment;
import pr.tongson.library.utils.LogUtils;

public class NetworkMonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.i("Tongson");

        setContentView(R.layout.network_monitor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NetworkMonitorFragment.newInstance()).commitNow();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("Tongson");

    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("Tongson");

    }
}