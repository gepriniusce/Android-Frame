package pr.tongson.template.pad.tab;

import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMViewModel;
import pr.tongson.library.mvvm.bindingproxy.BaseClickProxy;
import pr.tongson.library.mvvm.recycler.holder.RMVVMViewHolder;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.library.utils.L;
import pr.tongson.template.pad.R;
import pr.tongson.template.pad.databinding.ItemviewTabBinding;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/6
 * @Version
 * @Since
 * @Description
 */
public class TabViewItem extends BaseRMVVMViewItem<TabBean, ItemviewTabBinding> {

    @Override
    public BaseDataBindingConfig getDataBindingConfig() {
        return new BaseDataBindingConfig(R.layout.itemview_tab, new BaseMVVMViewModel());
    }

    @Override
    public void onBindViewHolder(ItemviewTabBinding binding, TabBean tabBean, RMVVMViewHolder holder) {
        binding.setTab(tabBean);
    }
}
