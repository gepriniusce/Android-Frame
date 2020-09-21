package pr.tongson.lib.http.ui.networksetting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import pr.tongson.lib.http.R;
import pr.tongson.lib.http.utils.NetworkRealUtils;
import pr.tongson.library.utils.NetworkUtils;

public class NetworkMonitorFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mViewModel;
    private View rootView;
    private EditText mEtIp;
    private Button mBtnPing;
    private TextView mTvNetworkRealConnected;

    public static NetworkMonitorFragment newInstance() {
        return new NetworkMonitorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.main_fragment, container, false);
            mEtIp = (EditText) rootView.findViewById(R.id.et_ip);
            mBtnPing = (Button) rootView.findViewById(R.id.btn_ping);
            mTvNetworkRealConnected = (TextView) rootView.findViewById(R.id.tv_network_real_connected);
            mBtnPing.setOnClickListener(this);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ping) {
            Toast.makeText(getActivity(), "NetworkUtils.isAvailableByPing(mEtIp.getText().toString()):\n" + NetworkUtils.isAvailableByPing(mEtIp.getText().toString()), Toast.LENGTH_SHORT).show();

            NetworkRealUtils.isNetworkRealConnected(() -> mTvNetworkRealConnected.setText("mIsNetworkAvailable:" + NetworkRealUtils.mIsNetworkAvailable));
        }
    }
}