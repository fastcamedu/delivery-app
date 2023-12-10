package com.fastcampus.deliveryapp.external.customer

data class CustomerRequest(
    val customerId: Long,
    val accessToken: String?,
)