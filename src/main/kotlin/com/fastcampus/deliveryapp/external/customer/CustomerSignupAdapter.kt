package com.fastcampus.deliveryapp.external.customer

import com.fastcampus.deliveryapp.domain.customer.CustomerSignup
import com.fastcampus.deliveryapp.domain.customer.CustomerSignupResult
import com.fastcampus.deliveryapp.service.customer.signup.CustomerSignupService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class CustomerSignupAdapter(
    private val restTemplate: RestTemplate,
): CustomerSignupService {

    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.signup}")
    private lateinit var signupPath: String

    companion object {
        private const val INVALID_CUSTOMER_ID = -1L
        private val logger = KotlinLogging.logger {  }
    }

    override fun signup(customerSignup: CustomerSignup): CustomerSignupResult {
        val signupFullPath = "$deliveryApiUrl$signupPath"
        logger.info { ">>> signup request: $signupFullPath, $customerSignup" }
        val customerSignupResult = restTemplate.postForEntity(signupFullPath, customerSignup, CustomerSignupResult::class.java)
        return CustomerSignupResult(
            customerId = customerSignupResult.body?.customerId ?: INVALID_CUSTOMER_ID
        )
    }
}