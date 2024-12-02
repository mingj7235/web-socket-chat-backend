package com.mj.chat.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.mj.chat.domain.chat.model.Message
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WssHandlerV1(
    private val objectMapper: ObjectMapper,
) : TextWebSocketHandler() {
    override fun handleTextMessage(
        session: WebSocketSession,
        message: TextMessage,
    ) {
        try {
            val payload = message.payload
            val messageFromPayload = objectMapper.readValue(payload, Message::class.java)

            /**
             * 1. DB 에 있는 데이터인지 확인 (from, to)
             * 2. 채팅 메세지 데이터 저장
             */
            session.sendMessage(TextMessage(payload))
        } catch (e: Exception) {
        }
    }
}
