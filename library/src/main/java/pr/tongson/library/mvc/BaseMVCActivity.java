package pr.tongson.library.mvc;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 * <p>
 * mvc就是
 * c=>逻辑直接丢activity上
 * v=>xml
 * m=>Bean
 * 正面刚不搞花里胡哨的
 *
 * @author tongson
 */
public abstract class BaseMVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        beforeOnCreate();
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        onViewCreated();
        setListener();
        processLogic(savedInstanceState);
    }

    /**
     * 有一些黑科技是在super.onCreate(savedInstanceState);之前执行的
     * 例如：换肤技术、侧划退出等。
     */
    protected void beforeOnCreate() {

    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 有一些事情是在initView()<-onViewCreated()->processLogic()之间执行的
     * 例如MVP的presenter的初始化
     */
    protected void onViewCreated() {

    }

    /**
     * 初始化ToolBar
     * 可选方法，毕竟不是所有activity的theme都是有ToolBar的
     */
    protected void initToolbar() {

    }

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    public Activity getActivity() {
        return this;
    }
}
