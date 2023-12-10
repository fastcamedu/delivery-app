package com.fastcampus.deliveryapp.controller.search.dto

import java.math.BigDecimal

data class StoreDTO(
    val storeId: Long,
    val storeName: String,
    val reviewGrade: Int,
    val deliveryFee: BigDecimal,
    val deliveryTime: String,
    val mainImageUrl: String
)
