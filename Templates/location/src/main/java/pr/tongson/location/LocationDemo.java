package pr.tongson.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import androidx.core.app.ActivityCompat;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/8/3
 * @Version
 * @Since
 * @Description
 */
public class LocationDemo {

    private Context mContext;
    private final static String TAG = "LocationDemo";
    private LocationManager locationManager;
    private MyLocationListener listeners[] = {new MyLocationListener(), new MyLocationListener()};

    public LocationDemo(Context context) {
        this.mContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void startRequestLocationUpdates() {
        if (ActivityCompat.
                checkSelfPermission(mContext, Manifest.
                        permission.
                        ACCESS_FINE_LOCATION) != PackageManager.
                PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(mContext, Manifest.
                        permission.
                        ACCESS_COARSE_LOCATION) != PackageManager.
                PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.
                requestLocationUpdates(LocationManager.
                        NETWORK_PROVIDER, 1000, 1f, listeners[0]);
        locationManager.
                requestLocationUpdates(LocationManager.
                        GPS_PROVIDER, 1000, 1F, listeners[1]);
    }

    public void stopRequestLocationUpdates() {
        locationManager.removeUpdates(listeners[0]);
        locationManager.removeUpdates(listeners[1]);
    }

    public Location getCurrentLocation() {
        // go in best to worst order
        for (int i = 0; i < listeners.length; i++) {
            Location l = listeners[i].current();
            if (l != null) {
                return l;
            }
        }
        Log.d(TAG, "No location received yet.");
        return null;
    }

    private class MyLocationListener implements LocationListener {
        Location mLastLocation = new Location("");
        boolean mValid = false;

        @Override
        public void onLocationChanged(Location newLocation) {
            if (newLocation.getLatitude() == 0.0 && newLocation.getLongitude() == 0.0) {
                // Hack to filter out 0.0,0.0 locations
                return;
            }
            if (!mValid) {
                Log.d(TAG, "Got first location.");
            }
            mLastLocation.set(newLocation);
            Log.d(TAG, "the newLocation is " + newLocation.getLongitude() + "x" + newLocation.getLatitude());
            mValid = true;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                case LocationProvider.TEMPORARILY_UNAVAILABLE: {
                    mValid = false;
                    break;
                }
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, " support current " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "no support current " + provider);
            mValid = false;
        }

        public Location current() {
            return mValid ? mLastLocation : null;
        }
    }


    public String getAddress(Location location) throws IOException {
        Geocoder geocoder = new Geocoder(mContext);
        boolean falg = Geocoder.isPresent();
        Log.e("xjp", "the falg is " + falg);
        StringBuilder stringBuilder = new StringBuilder();
        try {

            //根据经纬度获取地理位置信息
            //            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            //根据地址获取地理位置信息
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                //国家
                stringBuilder.append(address.getCountryName()).append("_");
                //周边地址
                stringBuilder.append(address.getFeatureName()).append("_");
                //市
                stringBuilder.append(address.getLocality()).append("_");
                stringBuilder.append(address.getPostalCode()).append("_");
                //国家编码
                stringBuilder.append(address.getCountryCode()).append("_");
                //省份
                stringBuilder.append(address.getAdminArea()).append("_");
                stringBuilder.append(address.getSubAdminArea()).append("_");
                //道路
                stringBuilder.append(address.getThoroughfare()).append("_");
                //香洲区
                stringBuilder.append(address.getSubLocality()).append("_");

                stringBuilder.append(address.getLatitude()).append("_");
                stringBuilder.append(address.getLongitude());//维度
                System.out.println(stringBuilder.toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(mContext, "报错", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
