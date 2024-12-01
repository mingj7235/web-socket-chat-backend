package com.mj.chat.common.exception

class CustomException : RuntimeException {
    private val errorCodeInterface: ErrorCodeInterface

    constructor(codeInterface: ErrorCodeInterface) : super(codeInterface.getMessage()) {
        this.errorCodeInterface = codeInterface
    }

    constructor(codeInterface: ErrorCodeInterface, message: String) : super(codeInterface.getMessage() + message) {
        this.errorCodeInterface = codeInterface
    }
}
