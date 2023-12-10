package com.fastcampus.deliveryapp.external.cart.dto

import java.math.BigDecimal

data class CartItemResponse(
    val cartItemId: Long,
    val menuId: Long,
    val menuName: String,
    val menuImageUrl: String,
    val quantity: Int,
    val totalPrice: BigDecimal,
)