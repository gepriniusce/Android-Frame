package pr.tongson.app

import androidx.multidex.MultiDex
import pr.tongson.library.jetpack.BaseJetpackApp
import pr.tongson.library.utils.L

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/21
 * @Version
 * @Since
 * @Description
 */
class AppApp : BaseJetpackApp() {
    override fun onCreate() {
        super.onCreate()
        // 主要是添加下面这句代码
        MultiDex.install(this)
        L.isDebug = true
        if (L.isDebug) {
            L.limitLogLevel = L.VERBOSE
        } else {
            L.limitLogLevel = L.ERROR
        }
        AppIntentService.startActionBaz(this, "", "")
        AppIntentService.startActionFoo(this, "", "")
    }
}