package com.fastcampus.deliveryapp.external.home.dto

import com.fastcampus.deliveryapp.domain.order.OrderStatus
import java.math.BigDecimal

data class RecentOrderStore(
    val orderId: Long,
    val orderStatus: OrderStatus,
    val storeId: Long,
    val storeName: String,
    val deliveryTime: String,
    val deliveryFee: BigDecimal,
    val mainImageUrl: String,
)