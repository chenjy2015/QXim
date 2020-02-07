package com.example.baselib.i

import com.example.baselib.bean.OpenAppVO
import com.example.baselib.bean.TuchongEntity

interface OnWorkbenchRequestListener {
    fun setBanners(banners: MutableList<TuchongEntity.FeedListBean.EntryBean>)
    fun failed(error: String)
    fun setOpenApps(apps: MutableList<OpenAppVO>)
}
