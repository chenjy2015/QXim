package com.example.qxim

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class GuideAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments = mutableListOf<Fragment>(GuideFragment(), GuideFragment2(), GuideFragment3())

    @SuppressLint("Recycle")
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}