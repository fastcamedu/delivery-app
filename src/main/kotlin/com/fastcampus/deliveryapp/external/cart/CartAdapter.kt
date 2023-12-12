package com.fastcampus.deliveryapp.external.cart

import com.fastcampus.deliveryapp.common.http.ExternalHttpApiUtils
import com.fastcampus.deliveryapp.common.http.HttpConstants
import com.fastcampus.deliveryapp.exception.NotFoundMenuException
import com.fastcampus.deliveryapp.external.cart.dto.CartItemRequest
import com.fastcampus.deliveryapp.external.cart.dto.CartRequest
import com.fastcampus.deliveryapp.external.cart.dto.CartResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.net.URI

@Component
class CartAdapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.cart-item}")
    private lateinit var cartItemPath: String

    fun findBy(cartRequest: CartRequest): CartResponse {
        val cartFullPath = "$deliveryApiUrl$cartItemPath?customerId=${cartRequest.customerId}"
        val headers = ExternalHttpApiUtils.getApiHeader(cartRequest.accessToken)
        val httpBody = LinkedMultiValueMap<String, String>()
        val request = HttpEntity(httpBody, headers)
        val responseEntity = restTemplate.exchange(cartFullPath, HttpMethod.GET, request, CartResponse::class.java)
        return responseEntity.body ?: throw NotFoundMenuException("장바구니 정보를 찾을 수 없습니다.")
    }

    fun add(customerId: Long, storeId: Long, menuId: Long, quantity: Int) {
        val cartFullPath = "$deliveryApiUrl$cartItemPath?customerId=$customerId&menuId=$menuId"
        return restTemplate.put(URI.create(cartFullPath), null)
    }

    fun count(cartItemRequest: CartItemRequest): Int {
        val cartItemFullPath = "$deliveryApiUrl$cartItemPath?customerId=${cartItemRequest.customerId}"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add(HttpConstants.HEADER_AUTHORIZATION, "Bearer ${cartItemRequest.accessToken}")

        val httpBody = LinkedMultiValueMap<String, String>()
        val request = HttpEntity(httpBody, headers)

        return try {
            val responseEntity = restTemplate.exchange(cartItemFullPath, HttpMethod.GET, request, CartResponse::class.java)
            responseEntity.body?.cartItems?.size ?: 0
        } catch (e: Exception) {
            0
        }
    }
}