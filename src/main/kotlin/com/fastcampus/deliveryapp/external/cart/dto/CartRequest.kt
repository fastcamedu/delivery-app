package com.fastcampus.deliveryapp.external.cart.dto

data class CartRequest(
    val customerId: Long,
    val accessToken: String?
)