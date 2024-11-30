package com.mj.chat.security

import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class HashManager {
    @OptIn(ExperimentalEncodingApi::class)
    fun getHashingValue(password: String): String {
        try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(password.toByteArray(StandardCharsets.UTF_8))

            return Base64.encode(hash)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Hash Failed", e)
        }
    }
}
