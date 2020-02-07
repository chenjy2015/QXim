package com.example.baselib.i

import androidx.fragment.app.Fragment

/**
 * 主页 fragment操作服务
 */
interface FragmentControlService {

    fun replaceFragment(targetFragment: Fragment, orientation: Orientation)

    fun addFragment(targetFragment: Fragment, orientation: Orientation)

    fun hideFragment(targetFragment: Fragment)

    fun showFragment(targetFragment: Fragment)

    fun deleteFragment(targetFragment: Fragment)

    fun popBackFragment()
}