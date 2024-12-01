package com.mj.chat.domain.auth.controller

import com.mj.chat.domain.auth.model.request.CreateUserRequest
import com.mj.chat.domain.auth.model.request.LoginRequest
import com.mj.chat.domain.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @Operation(
        summary = "Login User",
        description = "Login User",
    )
    @PostMapping("/login")
    fun login(
        @RequestBody @Valid
        request: LoginRequest,
    ) = authService.login(request)

    @Operation(
        summary = "get user name",
        description = "token 을 기반으로 username을 가져온다",
    )
    @GetMapping("/username/{token}")
    fun getUsernameFromToken(
        @PathVariable token: String,
    ): String = authService.getUsernameFromToken(token)
}
