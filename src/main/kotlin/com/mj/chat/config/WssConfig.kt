package com.mj.chat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler

@Configuration
@EnableWebSocket
class WssConfig : WebSocketConfigurer {
    // 임시 socket hander bean
    @Bean
    fun tempWebSocketHandler(): WebSocketHandler = object : TextWebSocketHandler() {}

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
            .addHandler(tempWebSocketHandler(), "/ws/v1/chat")
            .setAllowedOrigins("*")
    }
}
