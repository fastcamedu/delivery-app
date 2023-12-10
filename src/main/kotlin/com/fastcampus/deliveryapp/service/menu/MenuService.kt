package com.fastcampus.deliveryapp.service.menu

import com.fastcampus.deliveryapp.controller.display.dto.MenuDetailDTO
import com.fastcampus.deliveryapp.domain.menu.StoreMenu
import com.fastcampus.deliveryapp.external.menu.MenuDetailRequest

interface MenuService {
    fun list(storeId: Long): List<StoreMenu>
    fun detail(menuDetailRequest: MenuDetailRequest): MenuDetailDTO
}