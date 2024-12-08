package com.mj.chat.domain.chat.controller

import com.mj.chat.domain.chat.model.Message
import com.mj.chat.domain.chat.service.ChatServiceV1
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WssControllerV1(
    private val chatServiceV1: ChatServiceV1,
) {
    private val logger = LoggerFactory.getLogger(WssControllerV1::class.java)

    @MessageMapping("/chat/message/{from}") // client 로 보냄
    @SendTo("/sub/chat") // subscribe 하고 있음
    fun receivedMessage(
        @DestinationVariable from: String,
        message: Message,
    ): Message {
        logger.info("Meessage Received -> from $from, to : ${message.to}, message : ${message.message}")
        chatServiceV1.saveChatMessage(message)
        return message
    }
}
