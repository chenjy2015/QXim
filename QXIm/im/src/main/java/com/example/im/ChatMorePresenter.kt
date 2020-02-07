package com.example.im

import android.content.Context
import com.example.baselib.presenter.IPresenter




class ChatMorePresenter : IPresenter {

    fun loadMore(context: Context): MutableList<ChatMoreMenuVO> {
        val menus = arrayListOf<ChatMoreMenuVO>()
        val more = context.resources.getStringArray(R.array.chat_more)
        // icon
        val ar = context.resources.obtainTypedArray(R.array.chat_more_icon)
        val len = ar.length()
        val icons = IntArray(len)
        for (i in 0 until len) icons[i] = ar.getResourceId(i, 0)
        ar.recycle()

        for ((i, m) in more.withIndex()) {
            menus.add(ChatMoreMenuVO(icons[i], m))
        }
        return menus
    }

    fun loadMoreBy(context: Context, cutSize: Int): ArrayList<ArrayList<ChatMoreMenuVO>> {
        val menus = loadMore(context)
        val data: ArrayList<ArrayList<ChatMoreMenuVO>> = ArrayList<ArrayList<ChatMoreMenuVO>>()
        var count = 0
        for ((i, m) in menus.withIndex()) {
            if (i == 0) {
                data.add(arrayListOf())
                count = 0
            }
            if (data[count].size < cutSize) {
                data[count].add(m)
            } else if (data[count].size == cutSize) {
                data.add(arrayListOf())
                count++
                data[count].add(m)
            }
        }
        return data
    }
}