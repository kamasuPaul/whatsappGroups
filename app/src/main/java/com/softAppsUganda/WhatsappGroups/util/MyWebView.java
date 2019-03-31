package com.softAppsUganda.WhatsappGroups.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MyWebView extends WebViewClient {
    private Context context;
    private ProgressBar progressBar;

    public MyWebView(Context context,ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if(url.contains("whatsapp")&&url.contains("chat")){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }else {
            webView .loadUrl(url);
        }
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Toast.makeText(context,"Oops Error",Toast.LENGTH_SHORT).show();
    }
}
