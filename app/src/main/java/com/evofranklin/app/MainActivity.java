package com.evofranklin.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String HOME_URL = "http://3.0.149.255";

    private WebView webView;
    private ProgressBar progress;
    private View desktop;
    private View webContainer;
    private TextView streamStatus;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desktop = findViewById(R.id.desktop);
        webContainer = findViewById(R.id.webContainer);
        webView = findViewById(R.id.webview);
        progress = findViewById(R.id.progress);
        streamStatus = findViewById(R.id.streamStatus);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setMixedContentMode(
                WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        );

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                streamStatus.setText("STREAM ONLINE");
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progress.setProgress(newProgress);
                progress.setVisibility(
                        newProgress >= 100 ? View.GONE : View.VISIBLE
                );
            }
        });

        findViewById(R.id.btnConnect)
                .setOnClickListener(v -> openVirtualPhone());

        findViewById(R.id.btnPhone)
                .setOnClickListener(v -> openVirtualPhone());

        findViewById(R.id.btnHome)
                .setOnClickListener(v -> showDesktop());

        findViewById(R.id.btnBack)
                .setOnClickListener(v -> {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        showDesktop();
                    }
                });

        findViewById(R.id.btnReload)
                .setOnClickListener(v -> webView.reload());

        findViewById(R.id.btnBrowser).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(HOME_URL));
            startActivity(intent);
        });

        findViewById(R.id.btnApk).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("application/vnd.android.package-archive");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(
                        this,
                        "APK installer belum tersedia",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        findViewById(R.id.btnWhatsapp)
                .setOnClickListener(v ->
                        launchPackage("com.whatsapp", "WhatsApp"));

        findViewById(R.id.btnWhatsappBusiness)
                .setOnClickListener(v ->
                        launchPackage("com.whatsapp.w4b", "WhatsApp Business"));

        findViewById(R.id.btnTelegram)
                .setOnClickListener(v ->
                        launchPackage("org.telegram.messenger", "Telegram"));

        findViewById(R.id.btnFacebook)
                .setOnClickListener(v ->
                        launchPackage("com.facebook.katana", "Facebook"));

        findViewById(R.id.btnMessenger)
                .setOnClickListener(v ->
                        launchPackage("com.facebook.orca", "Messenger"));

        findViewById(R.id.btnInstagram)
                .setOnClickListener(v ->
                        launchPackage("com.instagram.android", "Instagram"));

        findViewById(R.id.btnDiscord)
                .setOnClickListener(v ->
                        launchPackage("com.discord", "Discord"));

        findViewById(R.id.btnSettings)
                .setOnClickListener(v ->
                        startActivity(
                                new Intent(Settings.ACTION_SETTINGS)
                        ));

        showDesktop();
    }

    private void openVirtualPhone() {
        streamStatus.setText("STREAM CONNECTING");
        desktop.setVisibility(View.GONE);
        webContainer.setVisibility(View.VISIBLE);
        webView.loadUrl(HOME_URL);
    }

    private void showDesktop() {
        webContainer.setVisibility(View.GONE);
        desktop.setVisibility(View.VISIBLE);
    }

    private void launchPackage(String packageName, String appName) {
        Intent intent = getPackageManager()
                .getLaunchIntentForPackage(packageName);

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(
                    this,
                    appName + " belum terinstall",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (webContainer.getVisibility() == View.VISIBLE) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                showDesktop();
            }
        } else {
            super.onBackPressed();
        }
    }
}