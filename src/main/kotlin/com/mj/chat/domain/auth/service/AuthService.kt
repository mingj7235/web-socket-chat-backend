package com.mj.chat.domain.auth.service

import com.mj.chat.domain.auth.model.request.CreateUserRequest
import com.mj.chat.domain.auth.model.response.CreateUserResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AuthService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun createUser(request: CreateUserRequest) = CreateUserResponse(request.name)
}
