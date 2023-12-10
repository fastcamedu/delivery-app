package com.fastcampus.deliveryapp.interceptor

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

class LoginInterceptor: HandlerInterceptor {

    companion object {
        private const val UNKNOWN_USER = "undefined"
        private val logger = KotlinLogging.logger {  }
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info { ">>> LoginInterceptor.preHandle" }
        if (isLoginUser(request)) {
            logger.info { ">>> 로그인 유저" }
            return super.preHandle(request, response, handler)
        }

        response.sendRedirect("/customer/login")
        return false
    }

    private fun isLoginUser(request: HttpServletRequest): Boolean {
        val accessToken = request.cookies?.firstOrNull { it.name == "access-token" }?.value
        val customerId = request.cookies?.firstOrNull { it.name == "customerId" }?.value
        if (accessToken == null || customerId == null || UNKNOWN_USER == accessToken || UNKNOWN_USER == customerId) {
            return false
        }
        return true
    }
}