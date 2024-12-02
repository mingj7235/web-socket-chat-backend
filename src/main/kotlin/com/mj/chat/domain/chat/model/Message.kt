package com.mj.chat.domain.chat.model

data class Message(
    val to: String,
    val from: String,
    val message: String,
)