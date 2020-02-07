package com.example.baselib.i

enum class Orientation(var value: Int) {
    LEFT(0),//从左往右进入
    TOP(1),//顶部向下进入
    RIGHT(2),//从右往左进入
    BOTTOM(3),//底部弹出
    NOTHING(-1) //不做任何动画
}