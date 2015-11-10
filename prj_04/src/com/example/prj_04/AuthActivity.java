package com.example.prj_04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergey on 10/11/15.
 */
public class AuthActivity extends Activity {
    public final static int ACCESS_TOKEN_TAKEN = 1;
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        webView = (WebView)findViewById(R.id.webView);

        webView.loadUrl("https://oauth.vk.com/authorize" +
                "?client_id=5141808" +
                "&scope=2097150" +
                "&redirect_uri=https://oauth.vk.com/blank.html" +
                "&display=mobile" +
                "&v=5.40" +
                "&response_type=token");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Matcher matcher = Pattern.compile(
                        "access_token=([0-9a-f]+)"
                ).matcher(url);

                if (matcher.find()) {
                    String access_token = matcher.group(1);
                    Intent intent = new Intent();
                    intent.putExtra("access_token", access_token);
                    AuthActivity.this.setResult(ACCESS_TOKEN_TAKEN, intent);

                    AuthActivity.this.finish();
                }
            }
        });

    }
}