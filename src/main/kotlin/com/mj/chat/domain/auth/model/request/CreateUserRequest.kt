package com.mj.chat.domain.auth.model.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(
    description = "Create User Request"
)
data class CreateUserRequest(
    @Schema(description = "User name")
    @NotBlank
    @NotNull
    val name: String,

    @Schema(description = "User password")
    @NotBlank
    @NotNull
    val password: String,
)
