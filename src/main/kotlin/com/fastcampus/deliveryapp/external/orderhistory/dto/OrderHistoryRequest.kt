package com.fastcampus.deliveryapp.external.orderhistory.dto

import com.fastcampus.deliveryapp.domain.order.OrderStatus

data class OrderHistoryRequest(
    val customerId: Long,
    val accessToken: String,
    val orderStatus: OrderStatus,
)