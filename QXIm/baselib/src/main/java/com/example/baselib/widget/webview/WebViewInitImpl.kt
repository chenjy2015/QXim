package com.example.baselib.widget.webview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * WebView初始化的定义
 */

class WebViewInitImpl(private val mActivity: Activity) : IWebViewInit {


    private var mWebChromeClient: WebChromeClientImpl? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun initWebView(webView: WebView): WebView {
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        val webSetting = webView.settings
        webSetting.userAgentString = USER_AGENT + webSetting.userAgentString

        webSetting.databaseEnabled = true
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSetting.textSize = WebSettings.TextSize.NORMAL

        // ===设置JS可用
        webSetting.javaScriptEnabled = true
        // JS打开窗口
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        // ===设置JS可用
        // 可以访问文件
        webSetting.allowFileAccess = true
        // ===缩放可用
        webSetting.setSupportZoom(true)
        webSetting.displayZoomControls = false //隐藏原生的缩放控件
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS //设置缩放功能   //能不能缩放 取决于网页设置
        webSetting.loadWithOverviewMode = true
        webSetting.builtInZoomControls = true
        // ===缩放可用
        // 支持多窗口
        webSetting.setSupportMultipleWindows(true)
        // ===============缓存
        webSetting.cacheMode = WebSettings.LOAD_DEFAULT// 决定是否从网络上取数据。
        webSetting.setAppCacheEnabled(true)
        // ===============缓存
        webSetting.useWideViewPort = true
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND)
        // ==定位
        webSetting.domStorageEnabled = true
        webSetting.setGeolocationEnabled(true)
        // ==定位
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 适配图片加载不出来的问题
            webSetting.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        return webView
    }

    override fun initWebViewClient(): WebViewClient {
        return WebViewClientImpl()
    }

    override fun initWebChromeClient(): WebChromeClient {
        mWebChromeClient = WebChromeClientImpl(mActivity)
        return mWebChromeClient as WebChromeClientImpl
    }

    /**
     * 页面标题、进度回调
     */
    fun setOnWebChromeListener(onWebChromeListener: WebChromeClientImpl.OnWebChromeListener) {
        if (mWebChromeClient != null) {
            mWebChromeClient!!.setOnWebChromeListener(onWebChromeListener)
        }

    }

    /**
     * 选择相机相册处理
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (mWebChromeClient != null) {
            mWebChromeClient!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {

        private val USER_AGENT = "appXXX"
    }
}