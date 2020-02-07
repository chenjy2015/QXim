package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProjectCenterVO(

    /**
     * 创建人id
     */
    val createdBy: Long,

    /**
     *创建时间
     */
    val createdTime: String,

    /**
     * 官网
     */
    val homeUrl: String,

    /**
     *
     */
    val id: Long,

    /**
     * logo地址
     */
    val logoPath: String,

    /**
     * 父id
     */
    val parentId: Long,

    /**
     * 产品描述
     */
    val prodDesc: String,

    /**
     * 产品名称
     */
    val prodName: String,

    /**
     * 用户类型（逗号分隔）
     */
    val userType: String
) : Parcelable