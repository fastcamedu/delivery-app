package com.fastcampus.deliveryapp.domain.menu

import com.fastcampus.deliveryapp.external.menu.MenuDTO

class StoreMenu (
    val storeId: Long,
    val menuIds: List<MenuDTO>
)