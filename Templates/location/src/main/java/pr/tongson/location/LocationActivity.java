package pr.tongson.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class LocationActivity extends FragmentActivity {

    private static final int INTERNET = 9527;
    private LocationDemo mLocation;
    private TextView mTvLocation;
    private TextView mTvAddress;
    private Button mBtnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mLocation = new LocationDemo(this);
        initView();
        setListener();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mLocation.startRequestLocationUpdates();
    }

    private void setListener() {
        mBtnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocation.getCurrentLocation() != null) {
                    mTvLocation.setText("getLongitude:" + mLocation.getCurrentLocation().getLongitude() + "getLatitude:" + mLocation.getCurrentLocation().getLatitude());
                    try {
                        mTvAddress.setText(mLocation.getAddress(mLocation.getCurrentLocation()));
                    } catch (IOException e) {
                        mTvAddress.setText(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initView() {
        mTvLocation = (TextView) findViewById(R.id.tv_location);
        mTvAddress = (TextView) findViewById(R.id.tv_Address);
        mBtnRequest = (Button) findViewById(R.id.btn_request);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocation != null) {
            mLocation.stopRequestLocationUpdates();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocation != null) {
            mLocation.stopRequestLocationUpdates();
            mLocation = null;
        }
    }
}