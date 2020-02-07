package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NavigationSecondVO(var navigationTitle: String, var selected: Boolean) : Parcelable