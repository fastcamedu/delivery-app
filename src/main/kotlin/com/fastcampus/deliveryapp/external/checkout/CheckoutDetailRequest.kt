package com.fastcampus.deliveryapp.external.checkout

data class CheckoutDetailRequest(
    val customerId: Long,
    val accessToken: String,
)
