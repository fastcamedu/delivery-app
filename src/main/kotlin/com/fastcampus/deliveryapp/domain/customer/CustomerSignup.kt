package com.fastcampus.deliveryapp.domain.customer

data class CustomerSignup(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val address: String,
)