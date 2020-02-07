package com.example.baselib.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResponseFile() : Parcelable {
    //文件全名
    var fileFullName: String? = null
    //  附件key值
    var fileKey: String? = null
    //    文件名称
    var fileName: String? = null
    // 文件存储路径
    var filePath: String? = null
    //     文件大小
    var fileSize: String? = null
    //      文件类型
    var fileType: String? = null
    // 文件访问路径
    var fileUrl: String? = null

    constructor(
        fileFullName: String,
        fileKey: String,
        fileName: String,
        filePath: String,
        fileSize: String,
        fileType: String,
        fileUrl: String
    ) : this() {
        this.fileFullName = fileFullName
        this.fileKey = fileKey
        this.fileName = fileName
        this.filePath = filePath
        this.fileSize = fileSize
        this.fileType = fileType
        this.fileUrl = fileUrl
    }


}