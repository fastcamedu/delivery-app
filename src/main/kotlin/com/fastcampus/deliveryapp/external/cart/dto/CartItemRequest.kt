package com.fastcampus.deliveryapp.external.cart.dto

data class CartItemRequest(
    val customerId: Long,
    val accessToken: String,
)
