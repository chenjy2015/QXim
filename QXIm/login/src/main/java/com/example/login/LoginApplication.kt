package com.example.login

import android.util.Log
import com.example.baselib.BaseApplication
import com.example.baselib.arouter.ARouterManager

class LoginApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "ClientApplication -- create()")
        ARouterManager.instance.init(this@LoginApplication)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouterManager.instance.destroy()
    }
}