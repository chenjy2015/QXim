package com.example.baselib.widget.webview

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * 对加载Url回调统一处理
 * 根据公司业务，自行处理
 */

class WebViewClientImpl : WebViewClient() {

    override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
        if (webView != null && url != null) {
            val context = webView!!.context
            if (url.endsWith(".apk")) {
                val apkUri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, apkUri)
                context.startActivity(intent)
            } else if (url.startsWith("http")) {
                webView!!.loadUrl(url)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                if (isInstall(context, intent)) {
                    context.startActivity(intent)
                }
            }
        }
        return true
    }

    // 判断app是否安装
    private fun isInstall(context: Context, intent: Intent): Boolean {
        return context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0
    }
}