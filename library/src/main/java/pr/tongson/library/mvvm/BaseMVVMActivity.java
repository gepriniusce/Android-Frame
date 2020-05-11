package pr.tongson.library.mvvm;


import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import pr.tongson.library.jetpack.BaseJetpackActivity;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/4/30
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseMVVMActivity extends BaseJetpackActivity {
    private ViewDataBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseDataBindingConfig dataBindingConfig = getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
    }

    protected ViewDataBinding getBinding() {
        return mBinding;
    }

    protected abstract BaseDataBindingConfig getDataBindingConfig();

}
