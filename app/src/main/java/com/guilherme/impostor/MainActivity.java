package com.guilherme.impostor;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int fundo = 0xFF14161D;
        getWindow().setStatusBarColor(fundo);
        getWindow().setNavigationBarColor(fundo);

        web = new WebView(this);
        web.setBackgroundColor(fundo);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    // Botão voltar: o jogo decide (fecha popup / pergunta se quer sair da partida)
    @Override
    public void onBackPressed() {
        web.evaluateJavascript("window.onAndroidBack && window.onAndroidBack()", valor -> {
            if (!"true".equals(valor)) MainActivity.super.onBackPressed();
        });
    }
}
