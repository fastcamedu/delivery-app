package com.fastcampus.deliveryapp.config

import com.fastcampus.deliveryapp.interceptor.LoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig: WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginInterceptor())
            .excludePathPatterns(
                "/error", "/hello",
                "/favicon.ico",
                "/customer/**",
                "/js/**", "/css/**", "/images/**"
            )
    }
}