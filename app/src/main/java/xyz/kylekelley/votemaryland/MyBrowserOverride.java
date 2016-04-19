package xyz.kylekelley.votemaryland;

import android.webkit.WebView;

/**
 * Created by Kimberly on 4/19/2016.
 */
public interface MyBrowserOverride {
    boolean ShouldOverrideUrlLoading(WebView view, String Url);
}
