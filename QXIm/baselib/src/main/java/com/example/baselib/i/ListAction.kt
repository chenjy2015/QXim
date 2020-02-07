package com.example.baselib.i

interface ListAction<T> {
    fun invoke(list: Collection<T>?)
}