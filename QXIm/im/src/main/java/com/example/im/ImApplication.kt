package com.example.im

import android.util.Log
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.example.baselib.BaseApplication
import com.example.baselib.arouter.ARouterManager

class ImApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "ImApplication -- create()")
        ARouterManager.instance.init(this@ImApplication)
        //emoji
        initEmoji()
    }

    fun initEmoji(){
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouterManager.instance.destroy()
    }
}