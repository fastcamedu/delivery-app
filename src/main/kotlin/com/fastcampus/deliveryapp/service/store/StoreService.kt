package com.fastcampus.deliveryapp.service.store

import com.fastcampus.deliveryapp.domain.store.CategoryStore
import com.fastcampus.deliveryapp.external.store.StoreDetailPageResponse

interface StoreService {
    fun list(categoryId: Long, reviewGradeFilterValue: Int): List<CategoryStore>
    fun detail(storeId: Long): StoreDetailPageResponse?
}

