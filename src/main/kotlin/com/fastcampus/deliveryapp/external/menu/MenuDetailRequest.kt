package com.fastcampus.deliveryapp.external.menu

data class MenuDetailRequest(
    val menuId: Long,
    val storeId: Long,
    val accessToken: String?,
)
