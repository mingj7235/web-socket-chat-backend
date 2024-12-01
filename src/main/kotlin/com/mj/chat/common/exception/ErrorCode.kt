package com.mj.chat.common.exception

enum class ErrorCode(
    private val code: Int,
    private val message: String,
) : ErrorCodeInterface {
    USER_ALREADY_EXISTS(0, "user already exists"),
    USER_SAVED_FAILED(1, "user saved failed"),
    NOT_EXIST_USER(2, "not exist user"),
    NOT_MATCH_PASSWORD(3, "not match password"),
    ;

    override fun getCode(): Int = code

    override fun getMessage(): String = message
}