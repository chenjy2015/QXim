package com.example.baselib.http

class ResponseParseBean<T : Any>(var body: T, var error: HttpThrowable)