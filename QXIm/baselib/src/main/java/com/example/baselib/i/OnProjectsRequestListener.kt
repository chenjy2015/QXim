package com.example.baselib.i

import com.example.baselib.bean.ProjectCenterVO

interface OnProjectsRequestListener {
    fun success(data: List<ProjectCenterVO>)
    fun failed(error: String)
}