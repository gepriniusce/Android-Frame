package pr.tongson.template.pad;

import androidx.lifecycle.MutableLiveData;
import pr.tongson.library.jetpack.BaseShareViewModel;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/6
 * @Version
 * @Since
 * @Description
 */
public class HomeActivityViewModel extends BaseShareViewModel {
    public final MutableLiveData<Integer> selectPos = new MutableLiveData<>();
    public final MutableLiveData<String> fragmentNames = new MutableLiveData<>();


    public String getFragmenetName() {
        return fragmentNames.getValue();
    }
}
