package com.mj.chat.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mj.chat.common.constants.Constants.Companion.ON_MINUTE_TO_MILLIS
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider(
    @Value("\${token.secret-key}") private val secretKey: String,
    @Value("\${token.token-time}") private val tokenTime: Long,
    @Value("\${token.refresh-secret-key}") private val refreshSecretKey: String,
    @Value("\${token.refresh-token-time}") private val refreshTokenTime: Long,
) {
    fun createToken(userName: String): String =
        JWT
            .create()
            .withSubject(userName)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + tokenTime * ON_MINUTE_TO_MILLIS))
            .sign(Algorithm.HMAC256(secretKey))

    fun createRefreshToken(userName: String): String =
        JWT
            .create()
            .withSubject(userName)
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + refreshTokenTime * ON_MINUTE_TO_MILLIS))
            .sign(Algorithm.HMAC256(refreshSecretKey))
}
