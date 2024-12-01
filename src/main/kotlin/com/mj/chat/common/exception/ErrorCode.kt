package com.mj.chat.common.exception

enum class ErrorCode(
    private val code: Int,
    private val message: String,
) : ErrorCodeInterface {
    USER_ALREADY_EXISTS(0, "user already exists"),
    USER_SAVED_FAILED(1, "user saved failed"),
    NOT_EXIST_USER(2, "not exist user"),
    NOT_MATCH_PASSWORD(3, "not match password"),

    ACCESS_TOKEN_IS_NOT_EXPIRED(4, "access token is not expired"),
    TOKEN_IS_INVALID(5, "access token is invalid"),
    TOKEN_IS_EXPIRED(6, "access token is expired"),
    ;

    override fun getCode(): Int = code

    override fun getMessage(): String = message
}
