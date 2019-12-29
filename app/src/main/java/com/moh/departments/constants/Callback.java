package com.moh.departments.constants;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Callback extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return (false);
    }
}
