package com.mj.chat.domain.auth.controller

import com.mj.chat.domain.auth.model.request.CreateUserRequest
import com.mj.chat.domain.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth API", description = "V1 Auth API")
@RestController
@RequestMapping("/api/v1/auth")
class AuthControllerV1(
    private val authService: AuthService,
) {
    @Operation(
        summary = "Create User",
        description = "Create User",
    )
    @PostMapping("/user")
    fun createUser(
        @RequestBody @Valid
        request: CreateUserRequest,
    ) = authService.createUser(request)
}
