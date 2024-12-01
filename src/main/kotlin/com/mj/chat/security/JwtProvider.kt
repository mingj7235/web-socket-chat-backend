package com.mj.chat.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.InvalidClaimException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.mj.chat.common.constants.Constants.Companion.ON_MINUTE_TO_MILLIS
import com.mj.chat.common.exception.CustomException
import com.mj.chat.common.exception.ErrorCode
import org.slf4j.LoggerFactory
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
    private val logger = LoggerFactory.getLogger(this::class.java)

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

    // refresh token 을 발행하기 위한 decode. 기존 token 이 expired 되어야 한다.
    fun checkTokenForRefresh(token: String): DecodedJWT {
        try {
            val decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token)
            logger.error("token must be expired, ${decodedJWT.subject}")
            throw CustomException(ErrorCode.ACCESS_TOKEN_IS_NOT_EXPIRED)
        } catch (e: Exception) {
            when (e) {
                is AlgorithmMismatchException, is SignatureVerificationException, is InvalidClaimException,
                -> throw CustomException(ErrorCode.TOKEN_IS_INVALID) // must be changed
                else -> throw e
            }
        } catch (e: TokenExpiredException) {
            // 기존 token 이 만료되었으므로, decode 를 수행한다.
            return JWT.decode(token)
        }
    }

    fun decodeAccessToken(token: String): DecodedJWT = decodeTokenAfterVerify(token, secretKey)

    fun decodeRefreshToken(token: String): DecodedJWT = decodeTokenAfterVerify(token, refreshSecretKey)

    // token 이 정상적으로 검증하고 decode 된 jwt 를 리턴하는 함수
    private fun decodeTokenAfterVerify(
        token: String,
        key: String,
    ): DecodedJWT {
        try {
            return JWT.require(Algorithm.HMAC256(key)).build().verify(token)
        } catch (e: Exception) {
            when (e) {
                is AlgorithmMismatchException, is SignatureVerificationException, is InvalidClaimException,
                -> throw CustomException(ErrorCode.TOKEN_IS_INVALID)
                else -> throw e
            }
        } catch (e: TokenExpiredException) {
            throw CustomException(ErrorCode.TOKEN_IS_EXPIRED)
        }
    }

    fun decodedJwt(token: String): DecodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token)

    fun getUserFromToken(token: String): String = decodedJwt(token).subject
}
