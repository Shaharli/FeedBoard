package com.avigezerit.feedboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

public class RssItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_item_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webview = (WebView) findViewById(R.id.rssFeedItemWebView);

        String url = this.getIntent().getStringExtra("URL");

        webview.loadUrl(url);

        this.setTitle("Rss Item");

    }

}
