package com.example.im

import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselib.arouter.ARouterConstants
import com.example.baselib.ui.fragment.BaseUIFragment
import com.example.baselib.utils.FragmentControlHelper
import com.example.im.databinding.FragmentMoreBinding
import com.tencent.smtt.utils.o

@Route(path = ARouterConstants.router_path_fragment.FRAGMENT_CHAT_MORE)
class ChatMoreFragment : BaseUIFragment<ChatMorePresenter, FragmentMoreBinding>() {

    var data = arrayListOf<ArrayList<ChatMoreMenuVO>>()
    lateinit var adapter: PagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_more
    }

    override fun init() {

    }

    override fun initEvent() {
    }

    override fun initData() {
        data = getPresenter().loadMoreBy(context!!, 8)
        adapter = MyPagerAdapter(childFragmentManager, data)
        getDataBinding().vp.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    class MyPagerAdapter : FragmentStatePagerAdapter {

        var menus = ArrayList<ArrayList<ChatMoreMenuVO>>()
        val fragments: ArrayList<Fragment> = arrayListOf()

        constructor(
            fragmentManager: FragmentManager,
            menus: ArrayList<ArrayList<ChatMoreMenuVO>>
        ) : super(fragmentManager) {
            this.menus = menus
        }

        override fun getItem(position: Int): Fragment {
            if (fragments.size <= position) {
                addFragment(position)
            }
            return fragments[position]
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }

        override fun getCount(): Int {
            return menus.size
        }

        fun addFragment(position: Int) {

            val fragment = FragmentControlHelper
                .getFragmentByParcelableList(
                    ARouterConstants.router_path_fragment.FRAGMENT_CHAT_MORE_MENU,
                    ARouterConstants.intent_key.CHAT_MORE_MENU,
                    menus[position] as ArrayList<Parcelable>
                )
            fragments.add(fragment)
        }
    }
}