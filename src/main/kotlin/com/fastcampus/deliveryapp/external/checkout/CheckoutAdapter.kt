package com.fastcampus.deliveryapp.external.checkout

import com.fastcampus.deliveryapp.common.http.ExternalHttpApiUtils
import com.fastcampus.deliveryapp.exception.NotFoundMenuException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class CheckoutAdapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.checkout}")
    private lateinit var checkoutPath: String

    fun findByCheckoutId(request: CheckoutDetailRequest): CheckoutDetailResponse {
        val checkoutFullPath = "$deliveryApiUrl$checkoutPath?customerId=${request.customerId}"

        val headers = ExternalHttpApiUtils.getApiHeader(request.accessToken)

        val httpBody = LinkedMultiValueMap<String, String>()

        val request = HttpEntity(httpBody, headers)

        val responseEntity = restTemplate.exchange(checkoutFullPath, HttpMethod.GET, request, CheckoutDetailResponse::class.java)
        return responseEntity.body ?: throw NotFoundMenuException("체크아웃 정보를 찾을 수 없습니다.")
    }
}