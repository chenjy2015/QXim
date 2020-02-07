package com.example.baselib.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.LogUtils
import com.example.baselib.bean.FragmentStatusVO
import com.example.baselib.enums.HiddenStatus
import com.example.baselib.presenter.IPresenter
import com.example.baselib.utils.FragmentStatusManager
import com.example.baselib.utils.PUtils
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer
import com.trello.rxlifecycle3.RxLifecycle
import com.trello.rxlifecycle3.android.FragmentEvent
import com.trello.rxlifecycle3.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject


/**
 * Create by Melorin on 2018/11/20
 */
abstract class BaseFragment<P : IPresenter> : Fragment(), LifecycleProvider<FragmentEvent> {


    private lateinit var presenter: P
    private var lifeSubject: BehaviorSubject<FragmentEvent> = BehaviorSubject.create()
    private var mCompositeDisposable: CompositeDisposable? = null
    lateinit var act: Activity

    private companion object {
        const val LOG_TAG = "BaseActivity"
    }

    fun getPresenter(): P {
        return presenter
    }

    override fun lifecycle(): Observable<FragmentEvent> {
        return lifeSubject.hide()
    }

    override fun <T> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifeSubject, event)
    }

    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindFragment(lifeSubject)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d("life", "onAttach")
        act = context as Activity
        lifeSubject.onNext(FragmentEvent.ATTACH)
        FragmentStatusManager.attach(FragmentStatusVO(this::class.java.name, HiddenStatus.DEFAULT,this))
    }

    override fun onDetach() {
        super.onDetach()
        FragmentStatusManager.detach(FragmentStatusVO(this::class.java.name, HiddenStatus.HIDDEN,this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("life", "onCreate")
        lifeSubject.onNext(FragmentEvent.CREATE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.d("life", "onActivityCreated")
        presenter = PUtils.createPresenter<P>(this@BaseFragment)!!
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("life", "onStart")
        lifeSubject.onNext(FragmentEvent.START)
    }


    override fun onResume() {
        super.onResume()
        lifeSubject.onNext(FragmentEvent.RESUME)
        FragmentStatusManager.attach(FragmentStatusVO(this::class.java.name, HiddenStatus.SHOW,this))
    }

    override fun onPause() {
        super.onPause()
        lifeSubject.onNext(FragmentEvent.PAUSE)
        FragmentStatusManager.attach(FragmentStatusVO(this::class.java.name, HiddenStatus.HIDDEN,this))
    }

    override fun onStop() {
        super.onStop()
        lifeSubject.onNext(FragmentEvent.STOP)
    }

    override fun onDestroy() {
        lifeSubject.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
        RxBus.getDefault().unregister(this)
        clearDisposable()
        FragmentStatusManager.detach(FragmentStatusVO(this::class.java.name, HiddenStatus.HIDDEN,this))
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            FragmentStatusManager.detach(FragmentStatusVO(this::class.java.name, HiddenStatus.HIDDEN,this))
        } else {
            FragmentStatusManager.detach(FragmentStatusVO(this::class.java.name, HiddenStatus.SHOW,this))
        }
    }


    /**
     * 添加订阅
     */
    fun addDisposable(mDisposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(mDisposable)
    }

    /**
     * 取消所有订阅
     */
    fun clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }
}