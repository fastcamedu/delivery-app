package com.fastcampus.deliveryapp.external.orderhistory.dto

import com.fastcampus.deliveryapp.domain.order.OrderStatus
import java.math.BigDecimal

data class OrderHistoryDTO(
    val orderId: Long,
    val orderStatus: OrderStatus,
    val storeId: Long,
    val storeName: String,
    val menuCount: Int,
    val menuNames: List<String>,
    val menuRepresentativeImageUrl: String,
    val totalOrderAmount: BigDecimal,
)
