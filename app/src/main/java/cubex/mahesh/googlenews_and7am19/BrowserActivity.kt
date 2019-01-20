package cubex.mahesh.googlenews_and7am19

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        var url = intent.getStringExtra("url")
        wview.loadUrl(url)
        wview.webViewClient = WebViewClient()
        wview.settings.javaScriptEnabled = true
        wview.settings.builtInZoomControls = true
    }
}
