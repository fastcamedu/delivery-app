package com.fastcampus.deliveryapp.external.home

import com.fastcampus.deliveryapp.common.http.HttpConstants
import com.fastcampus.deliveryapp.external.home.dto.DeliveryHomeResponse
import com.fastcampus.deliveryapp.external.home.dto.RecentOrderStoreRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class HomeAdapter(
    private val restTemplate: RestTemplate
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.home.recent-order}")
    private lateinit var favoriteOrderStorePath: String

    companion object {
        private val logger = KotlinLogging.logger { this::class::class.java }
    }

    fun findRecentOrderStores(recentOrderStoreRequest: RecentOrderStoreRequest): DeliveryHomeResponse?  {
        logger.info { ">>> findRecentOrderStores, customerId = ${recentOrderStoreRequest.customerId}" }
        val storeDetailFullPath = "$deliveryApiUrl$favoriteOrderStorePath?customerId=${recentOrderStoreRequest.customerId}"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add(HttpConstants.HEADER_AUTHORIZATION, "Bearer ${recentOrderStoreRequest.accessToken}")

        val httpBody = LinkedMultiValueMap<String, String>()

        val request = HttpEntity(httpBody, headers)
        val responseEntity = restTemplate.exchange(storeDetailFullPath, HttpMethod.GET, request, DeliveryHomeResponse::class.java)

        return responseEntity.body
    }
}