package com.fastcampus.deliveryapp.controller.customer.signup.dto

data class CustomerSignupRequest(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val address: String,
)