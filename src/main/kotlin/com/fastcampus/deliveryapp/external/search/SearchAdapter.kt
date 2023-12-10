package com.fastcampus.deliveryapp.external.search

import com.fastcampus.deliveryapp.controller.search.dto.StoreDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SearchAdapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.search}")
    private lateinit var searchPath: String

    fun search(keyword: String, reviewGradeFilterValue: Int): List<StoreDTO> {
        val menuDetailFullPath = "$deliveryApiUrl${searchPath}?keyword=$keyword&reviewGradeFilterValue=$reviewGradeFilterValue"
        val responseEntity = restTemplate.getForEntity(menuDetailFullPath, SearchResponse::class.java)
        val searchResponse = responseEntity.body
        if (searchResponse != null) {
            return searchResponse.stores
        }
        return emptyList()
    }
}