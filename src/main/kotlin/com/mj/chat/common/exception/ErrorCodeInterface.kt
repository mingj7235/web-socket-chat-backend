package com.mj.chat.common.exception

interface ErrorCodeInterface {
    fun getCode(): Int
    fun getMessage(): String
}