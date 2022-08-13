package com.example.webproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.webproject.R;
import com.example.webproject.base.CommonUtils;
import com.example.webproject.base.Constant;


import java.io.InputStream;

public class Scanner extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

            setTitle(R.string.lblQRScanner);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            webView = (WebView) findViewById(R.id.webview);
            webView.loadUrl(Constant.WHATSWEB_URL);
            webView.getSettings().setJavaScriptEnabled(true);



            // Add a WebViewClient
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    // Inject CSS when page is done loading
                    injectCSS();
                    super.onPageFinished(view, url);
                }
            });

            webView.getSettings().setUseWideViewPort(true);
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setNeedInitialFocus(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setBlockNetworkLoads(false);
            webView.getSettings().setBlockNetworkImage(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setInitialScale(100);

    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {

            super.onBackPressed();
            startActivity(new Intent(Scanner.this, HomeScreen.class));
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common,menu);
        return  true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mdirect:
                startActivity(new Intent(getApplicationContext(), DirectChat.class));
                return  true;
            case R.id.mstatus:
                startActivity(new Intent(getApplicationContext(),ImageAndVideo.class));
                return  true;
            case R.id.exit:
                CommonUtils.showDialog(getApplicationContext());
                return true;
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return false;
    }

private void injectCSS() {
    try {
        InputStream inputStream = getAssets().open("style.css");
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
        webView.loadUrl("javascript:(function() {" +
                "var parent = document.getElementsByTagName('head').item(0);" +
                "var style = document.createElement('style');" +
                "style.type = 'text/css';" +
                // Tell the browser to BASE64-decode the string into your script !!!
                "style.innerHTML = window.atob('" + encoded + "');" +
                "parent.appendChild(style)" +
                "})()");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
