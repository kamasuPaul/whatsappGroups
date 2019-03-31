package com.softAppsUganda.WhatsappGroups;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.softAppsUganda.WhatsappGroups.R;
import com.softAppsUganda.WhatsappGroups.util.MyWebView;

public class LovedFragment extends Fragment {
    String URL = "https://groupouts.com/category/";
    ProgressBar progressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_loved,container,false);
       WebView webView =  v.findViewById(R.id.webView);
       progressBar = v.findViewById(R.id.progressBarLoved);
        progressBar.setVisibility(View.VISIBLE);

        webView.loadUrl(URL);
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new MyWebView(getContext(),progressBar));
         return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
