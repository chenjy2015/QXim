package com.example.baselib.utils

import com.example.baselib.bean.FragmentStatusVO

class FragmentStatusManager {

    companion object {
        var fragments: ArrayList<FragmentStatusVO> = arrayListOf()

        /**
         * 添加或更新状态
         */
        fun attach(fragmentStatusVO: FragmentStatusVO) {
            val index = findIndex(fragmentStatusVO.tag)
            if (index != -1) {
                fragments[index].status = fragmentStatusVO.status
            } else {
                fragments.add(fragmentStatusVO)
            }
        }

        /**
         * 移除状态
         */
        fun detach(fragmentStatusVO: FragmentStatusVO) {
            val fragment = find(fragmentStatusVO.tag)
            if (fragment != null) {
                fragments.remove(fragment)
            }
        }

        fun find(tag: String): FragmentStatusVO? {
            for (fragment in fragments) {
                if (tag == fragment.tag) {
                    return fragment
                }
            }
            return null
        }

        fun findIndex(tag: String): Int {
            for ((index, fragment) in fragments.withIndex()) {
                if (tag == fragment.tag) {
                    return index
                }
            }
            return -1
        }
    }
}