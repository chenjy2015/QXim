package com.example.qxim

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.blankj.utilcode.util.PermissionUtils
import com.example.baselib.BaseApplication
import com.example.baselib.arouter.ARouterManager
import com.example.baselib.http.HttpRequestManager
import kotlin.system.exitProcess

@SuppressLint("WrongConstant")
class QXApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        PermissionUtils.permission(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).callback(object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                Log.d("Application", "MyApplication -- create()")
                ARouterManager.instance.init(this@QXApplication)
                HttpRequestManager.instance.init(this@QXApplication)
                initEmoji()
                Log.d("Application", "MyApplication -- create() complete")
            }

            override fun onDenied() {
                exitProcess(-1)
            }

        }).request()
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouterManager.instance.destroy()
    }


    fun initEmoji(){
        val config = BundledEmojiCompatConfig(this).setReplaceAll(true)
        EmojiCompat.init(config)
    }

//    fun initEmoji(){
//        val fontRequest = FontRequest(
//            "com.google.android.gms.fonts",
//            "com.google.android.gms",
//            "Noto Color Emoji Compat",
//            R.array.com_google_android_gms_fonts_certs
//        )
//        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
//            .setReplaceAll(true)
//            .registerInitCallback(object : EmojiCompat.InitCallback() {
//                override fun onInitialized() {
//                    Log.i("EmojiCompat", "onInitialized")
//                }
//                override fun onFailed(throwable: Throwable?) {
//                    Log.i("EmojiCompat", "onFailed")
//                    Log.i("EmojiCompat", Log.getStackTraceString(throwable))
//                }
//            })
//
//        EmojiCompat.init(config)
//    }
}