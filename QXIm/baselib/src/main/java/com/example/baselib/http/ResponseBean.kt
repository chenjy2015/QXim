package com.example.baselib.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ResponseBean<E>() : Parcelable {
    var code: Int = 0
    var error: HttpThrowable? = null
    var bean: E? = null

    constructor(code: Int, error: HttpThrowable?, bean: E?) : this() {
        this.code = code
        this.error = error
        this.bean = bean
    }

}