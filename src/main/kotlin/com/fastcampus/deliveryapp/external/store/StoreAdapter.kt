package com.fastcampus.deliveryapp.external.store

import com.fastcampus.deliveryapp.domain.store.CategoryStore
import com.fastcampus.deliveryapp.service.store.StoreService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class StoreAdapter(
    val restTemplate: RestTemplate
): StoreService {

    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.category.stores}")
    private lateinit var categoryStorePath: String

    @Value("\${apis.delivery-api.store-detail}")
    private lateinit var storeDetailPath: String

    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    override fun list(categoryId: Long, reviewGradeFilterValue: Int): List<CategoryStore> {
        val categoryStoreFullPath = "$deliveryApiUrl$categoryStorePath?categoryId=$categoryId&reviewGradeFilterValue=$reviewGradeFilterValue"

        logger.info { ">>> list, $categoryStoreFullPath, categoryId = $categoryId" }
        val responseEntity = restTemplate.getForEntity(categoryStoreFullPath, CategoryStoreResponse::class.java)
        return responseEntity.body?.categoryStores ?: emptyList()
    }

    override fun detail(storeId: Long): StoreDetailPageResponse? {
        val storeDetailFullPath = "$deliveryApiUrl$storeDetailPath$storeId"
        return restTemplate
            .getForObject(storeDetailFullPath, StoreDetailPageResponse::class.java)
    }
}

