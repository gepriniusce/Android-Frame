package pr.tongson.template.pad.applets;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;
import pr.tongson.library.mvvm.BaseDataBindingConfig;
import pr.tongson.library.mvvm.BaseMVVMFragment;
import pr.tongson.library.mvvm.BaseMVVMViewModel;
import pr.tongson.library.mvvm.recycler.adapter.RMVVMViewAdapter;
import pr.tongson.library.mvvm.recycler.item.BaseRMVVMViewItem;
import pr.tongson.library.recycler.listener.IOnItemListener;
import pr.tongson.library.utils.LogUtils;
import pr.tongson.template.pad.BR;
import pr.tongson.template.pad.R;
import pr.tongson.template.pad.applets.item.AppViewItem;
import pr.tongson.template.pad.applets.bean.SwanAppInfo;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends BaseMVVMFragment implements IOnItemListener {

    private ViewModel mViewModel;
    private BaseDataBindingConfig mDataBindingConfig;

    public CollectionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initViewModel() {
        mViewModel = new BaseMVVMViewModel();
    }

    @Override
    protected BaseDataBindingConfig getDataBindingConfig() {
        mDataBindingConfig = new BaseDataBindingConfig(R.layout.fragment_collection, mViewModel);
        List<SwanAppInfo> appInfos = new ArrayList<>();

        SwanAppInfo appInfo = new SwanAppInfo();
        appInfo.setAppTitle("标题");
        appInfo.setAppLogoUrl("www.xxx.pdf");
        appInfos.add(appInfo);
        appInfos.add(appInfo);
        appInfos.add(appInfo);
        appInfos.add(appInfo);
        appInfos.add(appInfo);

        BaseRMVVMViewItem[] itemTypes = new BaseRMVVMViewItem[1];
        itemTypes[0] = new AppViewItem();
        RMVVMViewAdapter adapter = new RMVVMViewAdapter(appInfos, itemTypes);
        adapter.setItemListener(this);
        mDataBindingConfig.addBindingParam(BR.adapter, adapter);
        return mDataBindingConfig;
    }

    @Override
    public void onItemClick(View view, Object entity, int position) {
        LogUtils.i("Tongson onItemClick");
        Toast.makeText(view.getContext(), "onItemClick:" + entity.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(View view, Object entity, int position) {
        Toast.makeText(view.getContext(), "onItemLongClick:" + entity.toString(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
