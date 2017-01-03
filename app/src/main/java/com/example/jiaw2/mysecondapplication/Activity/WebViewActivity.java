package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jiaw2.mysecondapplication.R;

/**
 * Created by jiaw2 on 2016/11/17.
 */
public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        webView.loadUrl("https://www.baidu.com/");
    }
}
