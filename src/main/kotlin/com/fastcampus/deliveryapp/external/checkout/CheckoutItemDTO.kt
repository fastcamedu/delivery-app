package com.fastcampus.deliveryapp.external.checkout

import java.math.BigDecimal

data class CheckoutItemDTO(
    val checkoutId: Long,
    val checkoutItemId: Long,
    val menuId: Long,
    val menuPrice: BigDecimal,
    val menuQuantity: Int,
)
