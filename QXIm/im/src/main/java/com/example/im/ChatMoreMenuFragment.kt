package com.example.im

import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.drakeet.multitype.MultiTypeAdapter
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.i.OnBinderItemClickListener
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.im.databinding.FragmentChatMoreMenuBinding


@Route(path = ARouterConstants.router_path_fragment.FRAGMENT_CHAT_MORE_MENU)
class ChatMoreMenuFragment : BaseUIFragment<ChatMorePresenter, FragmentChatMoreMenuBinding>(),
    OnBinderItemClickListener<ChatMoreMenuVO> {


    lateinit var adapter: MultiTypeAdapter
    lateinit var menus: MutableList<ChatMoreMenuVO>

    override fun getLayoutId(): Int {
        return R.layout.fragment_chat_more_menu
    }

    override fun init() {
        menus =
            arguments?.getParcelableArrayList<ChatMoreMenuVO>(ARouterConstants.intent_key.CHAT_MORE_MENU)!!
    }

    override fun initEvent() {
    }

    override fun initData() {
        adapter = MultiTypeAdapter()
        adapter.register(ChatMoreMenuVO::class.java, ChatMoreMenuBinder(this))
        adapter.items = menus
        adapter.notifyDataSetChanged()
        getDataBinding().recycler.adapter = adapter
        val gridLayoutManager = GridLayoutManager(context!!, 4)
        getDataBinding().recycler.layoutManager = gridLayoutManager

        val screenWidth = ScreenUtils.getScreenWidth() //屏幕宽度
        val itemWidth = SizeUtils.dp2px(60f) //每个item的宽度

        getDataBinding().recycler.addItemDecoration(
            RecycleItemSpance(30, 30, 30, 30, 4)
        )

    }

    override fun onItemClick(position: Int, t: ChatMoreMenuVO) {
        showLongToast(t.menu)
    }

}