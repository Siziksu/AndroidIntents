package com.siziksu.intents.ui.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.siziksu.intents.R;

public class WebViewActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Uri url = getIntent().getData();
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new LocalWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url.toString());
    }

    /**
     * Handling Page Navigation
     * The default web browser opens and loads the destination URL.
     * However, you can override this behavior for your WebView, so links open within your WebView.
     * You can then allow the user to navigate backward and forward through their web page history
     * that's maintained by your WebView.
     * To open links clicked by the user, simply provide a WebViewClient for your WebView, using
     * setWebViewClient().
     */
    private class LocalWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }
}
