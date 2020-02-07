package com.example.im

import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.emoji.EmojiConstant
import com.example.baselib.i.OnBinderItemClickListener
import com.example.baselib.presenter.LoginPresenter
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.im.databinding.FragmentEmojiBinding

class EmojiFragment : BaseUIFragment<LoginPresenter, FragmentEmojiBinding>(), OnBinderItemClickListener<String> {


    lateinit var adapter: MultiTypeAdapter
    lateinit var data: MutableList<String>
    lateinit var chatDataController: ChatDataController

    override fun getLayoutId(): Int {
        return R.layout.fragment_emoji
    }

    override fun init() {
        chatDataController = context as ChatDataController
    }

    override fun initEvent() {
    }

    override fun initData() {
        data = arrayListOf()
        data.addAll(EmojiConstant.emojis)
        adapter = MultiTypeAdapter()
        adapter.items = data
        adapter.register(String::class.java, ChatEmojiBInder(this))
        getDataBinding().recycler.adapter = adapter
        getDataBinding().recycler.layoutManager = GridLayoutManager(context!!,5)
    }

    override fun onItemClick(position: Int, t: String) {
        showLongToast(t)
        chatDataController.appendText(t)
    }

}