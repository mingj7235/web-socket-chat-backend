package com.mj.chat.domain.auth.service

import com.mj.chat.common.exception.CustomException
import com.mj.chat.common.exception.ErrorCode
import com.mj.chat.domain.auth.model.request.CreateUserRequest
import com.mj.chat.domain.auth.model.response.CreateUserResponse
import com.mj.chat.repository.UserRepository
import com.mj.chat.repository.entity.User
import com.mj.chat.repository.entity.UserCredentials
import com.mj.chat.security.HashManager
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val hashManager: HashManager,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional(transactionManager = "createUserTransactionManager")
    fun createUser(request: CreateUserRequest): CreateUserResponse {
        val user = userRepository.findByName(request.name)

        if (user.isPresent) {
            logger.error("user already exists, request : $request")
            throw CustomException(ErrorCode.USER_ALREADY_EXISTS)
        }

        try {
            val newUser = newUser(request.name)
            val newCredentials = newUserCredentials(request.password, newUser)
            newUser.updateCredentials(newCredentials)

            userRepository.save(newUser)
        } catch (e: Exception) {
            throw CustomException(ErrorCode.USER_SAVED_FAILED)
        }

        return CreateUserResponse(request.name)
    }

    private fun newUser(name: String): User =
        User(
            name = name,
        )

    private fun newUserCredentials(
        password: String,
        user: User,
    ): UserCredentials =
        UserCredentials(
            user = user,
            password = hashManager.getHashingValue(password),
        )
}
