package pr.tongson.module.mediaplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_video_player.*
import pr.tongson.library.utils.LogUtils

class VideoPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        initData()
    }

    fun initData() {
        val url = intent.getStringExtra("url")
        LogUtils.i("Tongson url:$url")
        if (TextUtils.isEmpty(url)) {
            return
        }
        videoView.setVideoPath(url)
        videoView.start()
    }
}
