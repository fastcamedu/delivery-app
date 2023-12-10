package com.fastcampus.deliveryapp.external.customer

data class CustomerResponse(
    val customerId: Long,
    val customerName: String,
    val phone: String,
    val address: String,
    val email: String,
)
