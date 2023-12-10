package com.fastcampus.deliveryapp.external.cart.dto

data class CartResponse(
    val customerId: Long,
    val cartItems: List<CartItemResponse>
)
