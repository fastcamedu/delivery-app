package com.fastcampus.deliveryapp.service.customer.signup

import com.fastcampus.deliveryapp.domain.customer.CustomerSignup
import com.fastcampus.deliveryapp.domain.customer.CustomerSignupResult
import org.springframework.stereotype.Service

@Service
interface CustomerSignupService {
    fun signup(customerSignup: CustomerSignup): CustomerSignupResult
}