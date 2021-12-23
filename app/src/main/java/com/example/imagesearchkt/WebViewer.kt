package com.example.imagesearchkt

import android.webkit.WebViewClient
import android.webkit.WebView
import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceRequest

class WebViewer {
    var webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }
    }
}