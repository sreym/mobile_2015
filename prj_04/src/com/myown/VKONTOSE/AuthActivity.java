package com.myown.VKONTOSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Interstellar on 10.11.2015.
 */
public class AuthActivity extends Activity {
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://oauth.vk.com/authorize" +
                "?client_id=5141824" +
                "&display=page" +
                "&redirect_uri=redirect_uri=https://oauth.vk.com/blank.html" +
                "&scope=friends" +
                "&response_type=token" +
                "&v=5.40");
      //  Activity activityRef = this;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Matcher matcher = Pattern.compile("access_token=([0-9a-f]+)").matcher(url);
                if (matcher.find()) {
                    String access_token = matcher.group(1);
                    Intent intent = new Intent();
                    intent.putExtra("access_token", access_token);
                    AuthActivity.this.setResult(0, intent);
                    AuthActivity.this.finish();
                }
            }
        });
    }
}