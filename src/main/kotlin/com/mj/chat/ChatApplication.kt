package com.mj.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ChatApplication

fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args)
}
