package com.example.baselib.widget.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView


/**
 * H5打开相机相册的回调监听
 * 进度条的回调监听
 */

class WebChromeClientImpl(private val mActivity: Activity?) : WebChromeClient() {
    /**
     * 进度条的回调监听
     */
    private var onWebChromeListener: OnWebChromeListener? = null

    /**
     * 打开相册 本地文件等等
     */
    private var uploadFile: ValueCallback<Uri>? = null
    private var uploadFiles: ValueCallback<Array<Uri>>? = null

    /**
     * 进度发生改变
     */
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        if (onWebChromeListener != null) {
            onWebChromeListener!!.onProgressChanged(view, newProgress)
        }
    }

    /**
     * 接收到标题
     */
    override fun onReceivedTitle(view: WebView, title: String) {
        if (onWebChromeListener != null) {
            onWebChromeListener!!.onReceivedTitle(view, title)
        }
    }

    // For Android 3.0+
    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
        handleFileChooser(uploadMsg, null)
    }

    // For Android < 3.0
    fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
        handleFileChooser(uploadMsg, null)
    }

    // For Android  > 4.1.1
    override fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
        handleFileChooser(uploadMsg, null)
    }

    // For Android  >= 5.0
    fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: IX5WebChromeClient.FileChooserParams
    ): Boolean {
        handleFileChooser(null, filePathCallback)
        return true
    }

    /**
     * 打开相册 本地文件等等
     */
    private fun handleFileChooser(uploadMsg: ValueCallback<Uri>?, filePathCallback: ValueCallback<Array<Uri>>?) {
        if (mActivity == null || mActivity.isFinishing) {
            return
        }
        uploadFile = uploadMsg
        uploadFiles = filePathCallback
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        mActivity.startActivityForResult(Intent.createChooser(intent, "请选择"), FILE_REQUEST_CODE)
    }

    /**
     * Activity回调处理
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 处理相机相册选择
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                FILE_REQUEST_CODE -> {
                    if (null != uploadFile) {
                        val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
                        uploadFile!!.onReceiveValue(result)
                        uploadFile = null
                    }
                    if (null != uploadFiles) {
                        val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
                        uploadFiles!!.onReceiveValue(result?.let { arrayOf<Uri>(it) })
                        uploadFiles = null
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile!!.onReceiveValue(null)
                uploadFile = null
            }
            if (uploadFiles != null) {
                uploadFiles!!.onReceiveValue(null)
                uploadFiles = null
            }
        }
    }


    // 页面标题、加载进度回调监听接口
    interface OnWebChromeListener {
        fun onReceivedTitle(view: WebView, title: String)

        fun onProgressChanged(view: WebView, newProgress: Int)
    }

    fun setOnWebChromeListener(onWebChromeListener: OnWebChromeListener) {
        this.onWebChromeListener = onWebChromeListener
    }

    companion object {
        // WebView打开相机相册的请求码
        val FILE_REQUEST_CODE = 0x011
    }

}