package com.fastcampus.deliveryapp.external.store

import com.fastcampus.deliveryapp.external.menu.MenuDTO
import java.math.BigDecimal

data class StoreDetailPageResponse(
    val storeId: Long,
    val storeName: String,
    val phone: String,
    val address: String,
    val deliveryFee: BigDecimal,
    val deliveryTime: String,
    val reviewGrade: Int,
    val storeMainImageUrl: String,
    val description: String,
    val minimumOrderPrice: BigDecimal,
    val menus: List<MenuDTO>
)
