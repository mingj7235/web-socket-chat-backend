package com.mj.chat.domain.auth.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Login Response")
class LoginResponse(
    @Schema(description = "jwt token")
    val token: String,
)
