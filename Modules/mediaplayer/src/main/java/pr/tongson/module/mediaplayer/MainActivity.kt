package pr.tongson.module.mediaplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity<VideoPlayerActivity>("url" to "https://media.w3.org/2010/05/sintel/trailer.mp4")
    }
}
