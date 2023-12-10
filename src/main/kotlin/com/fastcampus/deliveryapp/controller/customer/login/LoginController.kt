package com.fastcampus.deliveryapp.controller.customer.login

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

    @GetMapping("/customer/login")
    fun loginForm(): String {
        return "/customer/login/login"
    }
}