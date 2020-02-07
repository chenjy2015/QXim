package com.example.im

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ChatVO(var userId: String, var userName: String, var content: CharSequence, var headUrl: String) :
    Parcelable