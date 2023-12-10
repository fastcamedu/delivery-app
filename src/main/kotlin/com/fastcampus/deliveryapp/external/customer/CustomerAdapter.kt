package com.fastcampus.deliveryapp.external.customer

import com.fastcampus.deliveryapp.common.http.ExternalHttpApiUtils
import com.fastcampus.deliveryapp.exception.NotFoundMenuException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class CustomerAdapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.customer}")
    private lateinit var customerPath: String

    fun findByCustomerId(customerRequest: CustomerRequest): CustomerResponse {
        val customerPath = "$deliveryApiUrl$customerPath/${customerRequest.customerId}"

        val headers = ExternalHttpApiUtils.getApiHeader(customerRequest.accessToken)

        val httpBody = LinkedMultiValueMap<String, String>()
        httpBody.add("customerId", customerRequest.customerId.toString())

        val request = HttpEntity(httpBody, headers)

        val responseEntity = restTemplate.exchange(customerPath, HttpMethod.GET, request, CustomerResponse::class.java)

        return responseEntity.body ?: throw NotFoundMenuException("장바구니 정보를 찾을 수 없습니다.")
    }
}