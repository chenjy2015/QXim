package com.example.baselib.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.LogUtils
import com.example.baselib.ActivityCollector
import com.example.baselib.arouter.ARouterManager
import com.example.baselib.constants.SettingConst
import com.example.baselib.presenter.IPresenter
import com.example.baselib.utils.PUtils
import com.example.baselib.utils.statusbar.StatusBarUtil
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer
import com.trello.rxlifecycle3.RxLifecycle
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 *
 * @Author :  chenjiayou
 * @Dscription :  activity父类 集成共享数据和方法
 * @Create by : 2019/10/22
 *
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), LifecycleProvider<ActivityEvent> {

    private lateinit var presenter: P

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private companion object {
        var lifeSubject: BehaviorSubject<ActivityEvent> = BehaviorSubject.create()
        var mCompositeDisposable: CompositeDisposable? = null
        var mIsForeground: Boolean = false
        const val LOG_TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Android 8.0 系统一个限制 如果是透明背景则不能固定方向否则报错
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            val result = fixOrientation()
            LogUtils.i(LOG_TAG, "onCreate fixOrientation when Oreo, result = $result")
        }
        super.onCreate(savedInstanceState)
        try {
            if (supportActionBar != null) {
                supportActionBar?.hide()
            }
            //沉浸式代码配置
            //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
            StatusBarUtil.setRootViewFitsSystemWindows(this, true)
            //设置状态栏透明
            StatusBarUtil.setTranslucentStatus(this)
            //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
            //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this, 0x55000000)
//        }
        } catch (e: java.lang.Exception) {
            LogUtils.e(e)
        }

        ActivityCollector.addActivity(this@BaseActivity, this::class.java)
        presenter = PUtils.createPresenter<P>(this@BaseActivity)!!
        //Arouter注入
        ARouterManager.instance.inject(this@BaseActivity)
    }

    fun getPresenter(): P {
        return presenter
    }

    override fun lifecycle(): Observable<ActivityEvent> {
        return lifeSubject.hide()
    }

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindActivity(lifeSubject)
    }

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifeSubject, event)
    }

    @SuppressLint("PrivateApi")
    private fun isTranslucentOrFloating(): Boolean {
        var isTranslucentOrFloating = false
        try {
            val styleableRes =
                Class.forName("com.android.internal.R\$styleable").getField("Window").get(null) as IntArray
            val ta = obtainStyledAttributes(styleableRes)
            val m = ActivityInfo::class.java.getMethod("isTranslucentOrFloating", TypedArray::class.java)
            m.isAccessible = true
            isTranslucentOrFloating = m.invoke(null, ta) as Boolean
            m.isAccessible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isTranslucentOrFloating
    }

    private fun fixOrientation(): Boolean {
        try {
            val field = Activity::class.java.getDeclaredField("mActivityInfo")
            field.isAccessible = true
            val o = field.get(this) as ActivityInfo
            o.screenOrientation = -1
            field.isAccessible = false
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            LogUtils.i(LOG_TAG, "avoid calling setRequestedOrientation when Oreo.")
            return
        }
        super.setRequestedOrientation(requestedOrientation)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        lifeSubject.onNext(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lifeSubject.onNext(ActivityEvent.RESUME)
        mIsForeground = true
    }

    @CallSuper
    override fun onPause() {
        lifeSubject.onNext(ActivityEvent.PAUSE)
        super.onPause()
        mIsForeground = false
    }

    @CallSuper
    override fun onStop() {
        lifeSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        lifeSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
        RxBus.getDefault().unregister(this)
        cleanDisposable()
        ActivityCollector.removeActivity(this)
    }

    /**
     * 添加订阅
     */
    fun addDisposable(mDisposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(mDisposable)
    }

    /**
     * 取消所有订阅
     */
    fun cleanDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable?.clear()
        }
    }

    private var fontScale = -1f //动态设置放大倍数
    private val MULTIPLE = 1.0f //默认放大倍数
    private var languageType: String? = null

    fun setTextSizeModel(fontScale: Float) {
        this.fontScale = fontScale
    }

    fun setLanguageType(languageType: String) {
        this.languageType = languageType
    }

    override fun getResources(): Resources? {

        val resources = super.getResources()
        if (resources != null) {
            if (fontScale < 0) {
                fontScale = 1 * MULTIPLE
            }
            val dm = resources.displayMetrics
            val config = resources.configuration
            // 应用字号设置
            config.fontScale = fontScale
            // 应用用户选择语言
            if (languageType == null) {
                config.locale = Locale.getDefault()
            } else {
                config.locale =
                    if (languageType == SettingConst.LanguageType.Chinese) Locale.CHINESE else Locale.ENGLISH
            }
            resources.updateConfiguration(config, dm)
        }
        return resources
    }
}