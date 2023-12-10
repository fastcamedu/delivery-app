package com.fastcampus.deliveryapp.external.orderhistory

import com.fastcampus.deliveryapp.common.http.ExternalHttpApiUtils
import com.fastcampus.deliveryapp.exception.NotFoundException
import com.fastcampus.deliveryapp.external.orderhistory.dto.OrderHistoryRequest
import com.fastcampus.deliveryapp.external.orderhistory.dto.OrderHistoryResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class OrderHistoryAdapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.order-history}")
    private lateinit var orderHistoryPath: String

    fun list(request: OrderHistoryRequest): OrderHistoryResponse {
        val orderHistoryFullPath = "$deliveryApiUrl$orderHistoryPath?customerId=${request.customerId}&orderStatus=${request
            .orderStatus}"

        val headers = ExternalHttpApiUtils.getApiHeader(request.accessToken)

        val httpBody = LinkedMultiValueMap<String, String>()

        val request = HttpEntity(httpBody, headers)

        val responseEntity = restTemplate.exchange(orderHistoryFullPath, HttpMethod.GET, request, OrderHistoryResponse::class.java)
        return responseEntity.body ?: throw NotFoundException("주문 이력을 찾을 수 없습니다.")
    }
}