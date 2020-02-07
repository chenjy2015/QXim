package com.example.baselib.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResponseBodyBean<T>() : Parcelable {

    var data: T? = null
    var msg: String? = null
    var code: Int = 0

    constructor(data: T?, msg: String?, code: Int) : this() {
        this.data = data
        this.msg = msg
        this.code = code
    }
}
