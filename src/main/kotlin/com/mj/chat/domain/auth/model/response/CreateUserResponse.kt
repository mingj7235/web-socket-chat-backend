package com.mj.chat.domain.auth.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Create User Response")
data class CreateUserResponse(
    @Schema(description = "Result")
    val code: String,
)
