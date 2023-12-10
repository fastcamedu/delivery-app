package com.fastcampus.deliveryapp.controller.display.dto

import java.math.BigDecimal

data class MenuDetailDTO(
    val storeId: Long,
    val menuId: Long,
    val menuName: String,
    val menuPrice: BigDecimal,
    val menuMainImageUrl: String,
    val menuDescription: String,
)

