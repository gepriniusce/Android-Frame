package pr.tongson.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pr.tongson.lib.http.ui.networksetting.NetworkSettingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, NetworkSettingActivity::class.java))
    }
}
