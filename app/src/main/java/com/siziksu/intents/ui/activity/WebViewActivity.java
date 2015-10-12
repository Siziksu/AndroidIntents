package com.siziksu.intents.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.siziksu.intents.R;

public class WebViewActivity extends Activity {

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
    Uri url = getIntent().getData();
    WebView webView = (WebView) findViewById(R.id.webView);
    webView.setWebViewClient(new mWebViewCallback());
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
  private class mWebViewCallback extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      return false;
    }
  }
}
