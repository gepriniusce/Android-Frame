package pr.tongson.library.mvvm.bindingadpter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import pr.tongson.library.utils.ClickUtils;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/6
 * @Version
 * @Since
 * @Description 命令Binding适配器
 */
public class CommandBindingAdapter {
//    private CommandBindingAdapter() {
//        throw new UnsupportedOperationException("u can't instantiate me...");
//    }

    @BindingAdapter("onClickWithDebounce")
    public static void onClickWithDebounce(View view, View.OnClickListener clickListener) {
        ClickUtils.applySingleDebouncing(view, clickListener);
    }
}
