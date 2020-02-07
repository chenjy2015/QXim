package com.example.baselib.widget.webview

import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

interface IWebViewInit {
    /**
     * 1. 初始化和设置WebView
     */
    fun initWebView(webView: WebView): WebView

    /**
     * 2. 初始化WebViewClient
     */
    fun initWebViewClient(): WebViewClient

    /**
     * 3. 初始化WebChromeClient
     */
    fun initWebChromeClient(): WebChromeClient
}