package xyz.kylekelley.votemaryland;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Kimberly on 4/18/2016.
 */
public class external_webview extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("https://voterservices.elections.maryland.gov/VoterSearch");

    }

    private class MyBrowserOverride extends WebViewClient implements xyz.kylekelley.votemaryland.MyBrowserOverride {
        @Override
        public boolean ShouldOverrideUrlLoading(WebView view, String Url){
            view.loadUrl(Url);
            return true;
        }
    }


}
