package pr.tongson.template.pad.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMFragment;
import pr.tongson.library.mvvm.BaseMVVMViewModel;
import pr.tongson.library.mvvm.recycler.adapter.RMVVMViewAdapter;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.library.recycler.listener.IOnItemListener;
import pr.tongson.template.pad.BR;
import pr.tongson.template.pad.HomeActivityViewModel;
import pr.tongson.template.pad.R;

/**
 * @author v_luzhanneng
 */
public class TabFragment extends BaseMVVMFragment implements IOnItemListener {

    private ViewModel mViewModel;
    private HomeActivityViewModel mHomeActivityViewModel;

    private BaseDataBindingConfig mDataBindingConfig;

    public TabFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initViewModel() {
        mViewModel = new BaseMVVMViewModel();
        mHomeActivityViewModel = getActivityViewModel(HomeActivityViewModel.class);
    }

    @Override
    protected BaseDataBindingConfig getDataBindingConfig() {
        mDataBindingConfig = new BaseDataBindingConfig(R.layout.fragment_tab_list, mViewModel);
        List<TabBean> tabBeans = new ArrayList<>();
        TabBean tabBean0 = new TabBean("最近使用", false);
        TabBean tabBean1 = new TabBean("我的收藏", false);
        TabBean tabBean2 = new TabBean("常用分类", true);
        TabBean tabBean3 = new TabBean("热门推荐", false);
        tabBeans.add(tabBean0);
        tabBeans.add(tabBean1);
        tabBeans.add(tabBean2);
        tabBeans.add(tabBean3);

        BaseRMVVMViewItem[] itemTypes = new BaseRMVVMViewItem[1];
        itemTypes[0] = new TabViewItem();
        RMVVMViewAdapter adapter = new RMVVMViewAdapter(tabBeans, itemTypes);
        adapter.setItemListener(this);
        mDataBindingConfig.addBindingParam(BR.adapter, adapter);
        return mDataBindingConfig;
    }

    @Override
    public void onItemClick(View view, Object entity, int position) {
        mHomeActivityViewModel.selectPos.postValue(position);
       }

    @Override
    public boolean onItemLongClick(View view, Object entity, int position) {
        return true;
    }
}
