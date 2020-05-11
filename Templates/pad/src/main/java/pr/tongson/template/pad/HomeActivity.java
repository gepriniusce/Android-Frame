package pr.tongson.template.pad;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMActivity;

import android.os.Bundle;

public class HomeActivity extends BaseMVVMActivity {

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
