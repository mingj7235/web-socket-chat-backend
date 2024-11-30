package com.mj.chat.domain.auth.service

import com.mj.chat.domain.auth.model.request.CreateUserRequest
import com.mj.chat.domain.auth.model.response.CreateUserResponse
import com.mj.chat.repository.UserRepository
import com.mj.chat.repository.entity.User
import com.mj.chat.repository.entity.UserCredentials
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional(transactionManager = "createUserTransactionManager")
    fun createUser(request: CreateUserRequest): CreateUserResponse {
        val user = userRepository.findByName(request.name)

        if (user.isPresent) {
            // todo : error handling
        }

        try {
            val newUser = newUser(request.name)
            val newCredentials = newUserCredentials(request.password, newUser)
            newUser.updateCredentials(newCredentials)

            val savedUser = userRepository.save(newUser)

            if (savedUser == null) {
                // todo : error handling
            }
        } catch (e: Exception) {
            // todo : insert database error
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
    ): UserCredentials {
        // todo : hashing
        TODO()
    }
}
