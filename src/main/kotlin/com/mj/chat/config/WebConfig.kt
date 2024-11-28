package com.mj.chat.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/*") // 모든 경로 허용
            .allowedOriginPatterns("http://localhost:*")
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
            ).allowedHeaders("*")
            .allowCredentials(true) // 자격 증명 허용
            .maxAge(3600) // preflight 요청 캐시 시간
    }
}
