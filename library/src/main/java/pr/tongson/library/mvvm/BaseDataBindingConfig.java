package pr.tongson.library.mvvm;

import android.util.SparseArray;

import androidx.lifecycle.ViewModel;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/4
 * @Version
 * @Since
 * @Description
 */
public class BaseDataBindingConfig {
    private int layout;

    private ViewModel stateViewModel;

    private SparseArray bindingParams = new SparseArray();

    public BaseDataBindingConfig(int layout, ViewModel stateViewModel) {
        this.layout = layout;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }


    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public BaseDataBindingConfig addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}
