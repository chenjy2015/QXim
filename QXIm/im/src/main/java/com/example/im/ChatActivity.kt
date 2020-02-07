package com.example.im

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.emoji.text.EmojiCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.KeyboardUtils
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.bean.UserInfo
import com.example.baselib.i.SimpleTextWatcher
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.activity.BaseUIActivity
import com.example.baselib.utils.statusbar.StatusBarUtil
import com.example.im.databinding.ActivityChatBinding
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.chat_bottom.view.*
import kotlinx.android.synthetic.main.toolbar_chat.view.*
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit


@Route(path = ARouterConstants.router_path_activity.ACTIVITY_PATH_CHAT)
class ChatActivity : BaseUIActivity<LoginPresenter, ActivityChatBinding>(), OnRefreshListener,
    OnLoadMoreListener, ChatDataController {

    lateinit var data: MutableList<ChatVO>
    lateinit var adapter: MultiTypeAdapter
    private var userInfo: UserInfo? = null
    private var emojiFragment: EmojiFragment? = null
    private var moreFragment: ChatMoreFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun init() {
        StatusBarUtil.setStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.background_color_1)
        )
        userInfo = getPresenter().getUser()
        if (userInfo != null) {
            getDataBinding().toolbar.tv_title.text = userInfo!!.account
        } else {
            getDataBinding().toolbar.tv_title.text = "华润万家"
        }
        getDataBinding().refreshLayout.setEnableLoadMore(false)

    }

    override fun initEvent() {
        getDataBinding().toolbar.fl_back.setOnClickListener {
            showShortToast("back clicked !")
        }
        getDataBinding().toolbar.fl_menu.setOnClickListener {
            showShortToast("menu clicked !")
        }
        getDataBinding().input.iv_emoji.setOnClickListener {
            toggleEmoji()
        }
        getDataBinding().input.iv_add.setOnClickListener {
            toggleMore()
        }

        getDataBinding().input.et_chat.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
            }
        })
        getDataBinding().input.et_chat.setOnEditorActionListener(object :
            TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendText(getDataBinding().input.et_chat.text.toString())
                    getDataBinding().input.et_chat.setText("")
                    return true
                }
                return false
            }
        })
        getDataBinding().refreshLayout.setOnRefreshListener(this)
        getDataBinding().refreshLayout.setOnLoadMoreListener(this)
    }

    override fun initData() {
        data = arrayListOf()
        adapter = MultiTypeAdapter()
        adapter.register(ChatVO::class.java, ChatTextBinder())
        adapter.items = data
        getDataBinding().recycler.adapter = adapter
        getDataBinding().recycler.layoutManager = LinearLayoutManager(this)
    }

    private fun refresh() {
        val temp = arrayListOf<ChatVO>()
        temp.addAll(data)
        data.clear()
        adapter.notifyItemRangeRemoved(0, temp.size)
        data.addAll(temp)
        adapter.notifyItemRangeInserted(0, data.size)
        temp.clear()
    }

    private fun loadMore() {
        val temp = arrayListOf<ChatVO>()
        temp.addAll(data)
        data.clear()
        adapter.notifyItemRangeRemoved(0, temp.size)
        data.addAll(temp)
        adapter.notifyItemRangeInserted(0, data.size)
        temp.clear()
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        addDisposable(
            Observable.just("")
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    refresh()
                    refreshLayout.finishRefresh()
                }
        )
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        addDisposable(
            Observable.just("")
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loadMore()
                    refreshLayout.finishLoadMore()
                }
        )
    }

    var showEmoji = false
    fun toggleEmoji() {
        moreFragment = null
        if (emojiFragment == null) {
            emojiFragment = EmojiFragment()
            supportFragmentManager.beginTransaction()
                .replace(getDataBinding().input.fl_more.id, emojiFragment!!)
                .commitAllowingStateLoss()
            return
        }
        if (showEmoji) {
            getDataBinding().input.fl_more.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().show(emojiFragment!!)
                .commitAllowingStateLoss()
        } else {
            getDataBinding().input.fl_more.visibility = View.GONE
            supportFragmentManager.beginTransaction().hide(emojiFragment!!)
                .commitAllowingStateLoss()
        }
        showEmoji = !showEmoji
    }

    var showMore = false
    fun toggleMore(){
        emojiFragment = null
        if (moreFragment == null) {
            moreFragment = ChatMoreFragment()
            supportFragmentManager.beginTransaction()
                .replace(getDataBinding().input.fl_more.id, moreFragment!!)
                .commitAllowingStateLoss()
            return
        }
        if (showMore) {
            getDataBinding().input.fl_more.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().show(moreFragment!!)
                .commitAllowingStateLoss()
        } else {
            getDataBinding().input.fl_more.visibility = View.GONE
            supportFragmentManager.beginTransaction().hide(moreFragment!!)
                .commitAllowingStateLoss()
        }
        showMore = !showMore
    }

    override fun sendText(s: String) {
        KeyboardUtils.hideSoftInput(getDataBinding().input.et_chat)
        val chatVO = ChatVO(
            System.currentTimeMillis().toString(),
            "joke",
            s,
            ChatConstant.url
        )
        data.add(chatVO)
        adapter.notifyItemInserted(data.size - 1)
        getDataBinding().recycler.scrollToPosition(data.size - 1)
    }

    override fun appendText(s: String) {
        val char = getDataBinding().input.et_chat.text.toString()
        val sb = StringBuilder().append(char).append(s)
        val str = EmojiCompat.get().process(sb.toString())
        getDataBinding().input.et_chat.setText(str)
        getDataBinding().input.et_chat.setSelection(str.length)
    }
}
