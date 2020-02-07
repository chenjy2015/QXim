package com.example.baselib.enums

enum class UserStatus(var status: Int) {
    //0未激活1正常2已冻结3已注销
    NOTACTIVATED(0),
    NORMAL(1),
    FROZEN(2),
    LOGGEDOFF(3)
}