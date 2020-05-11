package pr.tongson.template.pad.applets.item;

import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMViewModel;
import pr.tongson.library.mvvm.recycler.holder.RMVVMViewHolder;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.template.pad.R;
import pr.tongson.template.pad.applets.bean.SwanAppInfo;
import pr.tongson.template.pad.databinding.ItemviewAppBinding;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/6
 * @Version
 * @Since
 * @Description
 */
public class AppViewItem extends BaseRMVVMViewItem<SwanAppInfo, ItemviewAppBinding> {
    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        return new BaseDataBindingConfig(R.layout.itemview_app,new BaseMVVMViewModel());
    }

    @Override
    public void onBindViewHolder(ItemviewAppBinding binding, SwanAppInfo swanAppInfo, RMVVMViewHolder holder) {
        binding.setApp(swanAppInfo);
        if (getClickProxy() != null) {
            binding.setCmd(getClickProxy());
        }
    }
}
