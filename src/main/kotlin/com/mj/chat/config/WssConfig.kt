package com.mj.chat.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocket
class WssConfig : WebSocketMessageBrokerConfigurer {
    // pub sub 에 대한 명시
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            .enableSimpleBroker("/sub")
        registry
            .setApplicationDestinationPrefixes("/pub")
    }

    // web socket 통신에 연결되는 기본적인 연결 설정
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/ws-stomp")
            .setAllowedOrigins("*")
//            .withSockJS() // 이 옵션은 client 가 websocket 을 사용할 수 없는 경우에, 방어로직으로 사용할 수 있는 옵션 (브라우저 버전이 너무 낮아서 websocket 을 지원하지 않는 경우) 이 옵션을 키면 http 통신을 함 (long polling)
    }
}
