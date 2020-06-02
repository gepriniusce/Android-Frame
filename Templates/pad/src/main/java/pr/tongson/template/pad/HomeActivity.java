package pr.tongson.template.pad;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class HomeActivity extends BaseMVVMActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 667;
    private BaseDataBindingConfig mDataBindingConfig;
    private HomeActivityViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityViewModel(HomeActivityViewModel.class);
    }

    @Override
    protected BaseDataBindingConfig getDataBindingConfig() {
        mDataBindingConfig = new BaseDataBindingConfig(R.layout.activity_home, mViewModel);
        return mDataBindingConfig;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabSelect();

        usePermissions();


    }

    private void usePermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void tabSelect() {
        NavController navController = Navigation.findNavController(this, R.id.fl_value);
        mViewModel.selectPos.observe(this, integer -> {
            //NavHostFragment.findNavController();
            //            Navigation.findNavController(View)
            switch (integer) {
                case 0:
                default:
                    navController.navigate(R.id.recentlyFragment);
                    break;
                case 1:
                    navController.navigate(R.id.collectionFragment);
                    break;
                case 2:
                    navController.navigate(R.id.commonlyFragment);
                    break;
                case 3:
                    navController.navigate(R.id.popularFragment);
                    break;
            }
        });
    }
}
