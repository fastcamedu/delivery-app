package com.fastcampus.deliveryapp.external.menu

import java.math.BigDecimal

data class MenuDetailResponse(
    val menuId: Long,
    val menuName: String,
    val storeId: Long,
    val description: String,
    val menuMainImageUrl: String,
    val price: BigDecimal,
    val menuStatue: String,
)