package com.example.baselib.enums

enum class OperatorType(var type: Int) {

    /**
     * 1、绑定邮箱 2、找回密码 3、修改密码 4、绑定手机 5、变更邮箱 6、变更手机 7、合作意向 8、大使意向
     */
    BIND_EMAIL(1),
    FIND_PASSWORD(2),
    UPDATE_PASSWORD(3),
    BIND_MOBILE(4),
    UPDATE_EMAIL(5),
    UPDATE_MOBILE(6),
    COOPERATION_INTENT(7),
    AMBASSADOR_INTENT(1)
}