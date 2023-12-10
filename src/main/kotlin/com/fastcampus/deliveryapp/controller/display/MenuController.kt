package com.fastcampus.deliveryapp.controller.display

import com.fastcampus.deliveryapp.controller.display.dto.MenuDetailDTO
import com.fastcampus.deliveryapp.external.menu.MenuDetailRequest
import com.fastcampus.deliveryapp.service.menu.MenuService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MenuController(
    val menuService: MenuService,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/display/menus/{menuId}")
    fun detail(
        @PathVariable menuId: Long,
        @RequestParam storeId: Long,
        @CookieValue(value = "customerId") customerId: Long?,
        @CookieValue(value = "access-token") accessToken: String?,
        model: Model
    ): String {
        logger.info { ">>> request menu detail: $menuId"}
        val menuDetail = menuService.detail(
            MenuDetailRequest(menuId = menuId, storeId = storeId, accessToken = accessToken)
        )

        validateStoreId(menuDetail, storeId)

        model.addAttribute("menuDetail", menuDetail)
        model.addAttribute("storeId", storeId)
        model.addAttribute("customerId", customerId)

        return "/display/menu/menu-detail"
    }

    private fun validateStoreId(
        menuDetail: MenuDetailDTO,
        storeId: Long
    ) {
        if (menuDetail.storeId != storeId) {
            throw IllegalArgumentException("Invalid store id: $storeId")
        }
    }
}