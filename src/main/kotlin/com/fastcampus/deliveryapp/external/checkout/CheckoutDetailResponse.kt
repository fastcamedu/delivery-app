package com.fastcampus.deliveryapp.external.checkout

import java.math.BigDecimal

data class CheckoutDetailResponse(
    val customerId: Long,
    val checkoutId: Long,
    val totalAmount: BigDecimal,
    val checkoutItems: List<CheckoutItemDTO>
)