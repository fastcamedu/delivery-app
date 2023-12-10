package com.fastcampus.deliveryapp.controller.customer.signup

import com.fastcampus.deliveryapp.controller.customer.signup.dto.CustomerSignupRequest
import com.fastcampus.deliveryapp.domain.customer.CustomerSignup
import com.fastcampus.deliveryapp.service.customer.signup.CustomerSignupService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class CustomerSignupController(
    private val customerSignupService: CustomerSignupService
) {

    companion object {
        private const val UNKNOWN_CUSTOMER_ID = 0L
    }

    @GetMapping("/customer/signup")
    fun signupForm(): String {
        return "/customer/signup/signup"
    }

    @PostMapping("/customer/signup")
    fun signup(@ModelAttribute customerSignupRequest: CustomerSignupRequest, model: Model): String {
        val customerSignup = CustomerSignup(
            email = customerSignupRequest.email,
            password = customerSignupRequest.password,
            name = customerSignupRequest.name,
            phone = customerSignupRequest.phone,
            address = customerSignupRequest.address,
        )
        val customerSignupResult = customerSignupService.signup(customerSignup)
        return if (customerSignupResult.customerId > UNKNOWN_CUSTOMER_ID) {
            "redirect:/customer/signup/success"
        } else {
            "redirect:/customer/signup/fail"
        }
    }

    @GetMapping("/customer/signup/success")
    fun success(): String {
        return "/"
    }

    @GetMapping("/customer/signup/fail")
    fun fail(): String {
        return "/customer/signup/signup-fail"
    }
}