package com.example.baselib.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.baselib.R
import com.example.baselib.enums.HiddenStatus
import com.example.baselib.i.Orientation

/**
 * @author: chenjiayou
 * @createBy: 2019/10/30
 * @descript:  fragment切换控制器 可全局操控
 */
class FragmentControlManager constructor(private var fragmentManager: FragmentManager, private var mainId: Int) {

    fun replaceFragment(targetFragment: Fragment, orientation: Orientation) {
        val transaction = fragmentManager.beginTransaction()
        toTransaction(transaction, orientation)
            .replace(mainId, targetFragment, "")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(targetFragment::javaClass.name)
            .commitAllowingStateLoss()
    }


    fun addFragment(targetFragment: Fragment, orientation: Orientation) {
//        if (FragmentStatusManager.find(targetFragment::class.java.name) != null) {
//            switchFragment(FragmentStatusManager.find(targetFragment::class.java.name)!!.fragment)
//        } else {
            attach(targetFragment, orientation)
//        }
    }

    private fun attach(targetFragment: Fragment, orientation: Orientation) {
        val transaction = fragmentManager.beginTransaction()
        toTransaction(transaction, orientation)
            .add(mainId, targetFragment, System.currentTimeMillis().toString())
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(targetFragment::javaClass.name)
            .commitAllowingStateLoss()
    }

    private fun toTransaction(transaction: FragmentTransaction, orientation: Orientation): FragmentTransaction {
        return if (orientation == Orientation.RIGHT) {
            transaction.setCustomAnimations(
                R.anim.slide_right_in,
                R.anim.slide_left_out,
                R.anim.slide_left_in,
                R.anim.slide_right_out
            )
        } else {
            transaction.setCustomAnimations(
                R.anim.slide_bottom_in,
                R.anim.slide_top_out,
                R.anim.slide_top_in,
                R.anim.slide_bottom_out
            )
        }
    }

    fun deleteFragment(targetFragment: Fragment) {
        fragmentManager.beginTransaction().remove(targetFragment).commitAllowingStateLoss()
    }

    fun popBackFragment() {
        fragmentManager.popBackStack()
    }

    fun hasFragment(targetFragment: Fragment): Boolean {
        return fragmentManager.fragments.contains(targetFragment)
    }

    fun switchFragment(to: Fragment) {
        var currentIndex = 0
        //找出当前fragment所处于栈中位置
        for ((index,fragment) in fragmentManager.fragments.withIndex()){
            if (fragment == to){
                currentIndex = index
                break
            }
        }
        //将栈中处于当前fragment之上的fragment全部隐藏
        for ((index,fragment) in fragmentManager.fragments.withIndex()){
            if (index <= currentIndex){
                continue
            }
            fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss()
        }
        fragmentManager.beginTransaction().show(to).commitAllowingStateLoss()
    }

    fun showFragment(targetFragment: Fragment) {
        fragmentManager.beginTransaction().show(targetFragment).commitAllowingStateLoss()
    }

    fun hideFragment(targetFragment: Fragment) {
        fragmentManager.beginTransaction().hide(targetFragment).commitAllowingStateLoss()
    }
}