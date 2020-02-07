package com.example.baselib.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResponseBodyListBean<E>() : Parcelable {

    var data: List<E>? = null
    lateinit var msg: String
    var code: Int = 0

    constructor(data: List<E>, msg: String, code: Int) : this() {
        this.data = data
        this.msg = msg
        this.code = code
    }
}
