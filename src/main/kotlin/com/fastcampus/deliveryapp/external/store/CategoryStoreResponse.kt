package com.fastcampus.deliveryapp.external.store

import com.fastcampus.deliveryapp.domain.store.CategoryStore

data class CategoryStoreResponse(
    val categoryStores: List<CategoryStore>
)