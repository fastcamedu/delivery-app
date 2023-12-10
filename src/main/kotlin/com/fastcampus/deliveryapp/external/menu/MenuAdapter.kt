package com.fastcampus.deliveryapp.external.menu

import com.fastcampus.deliveryapp.common.http.ExternalHttpApiUtils
import com.fastcampus.deliveryapp.controller.display.dto.MenuDetailDTO
import com.fastcampus.deliveryapp.domain.menu.StoreMenu
import com.fastcampus.deliveryapp.exception.NotFoundMenuException
import com.fastcampus.deliveryapp.service.menu.MenuService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class MenuAdapter(
    private val restTemplate: RestTemplate,
): MenuService {

    @Value("\${apis.delivery-api.host}")
    private lateinit var deliveryApiUrl: String

    @Value("\${apis.delivery-api.menu-detail}")
    private lateinit var menuDetailPath: String

    override fun list(storeId: Long): List<StoreMenu> {
        return emptyList()
    }

    override fun detail(menuDetailRequest: MenuDetailRequest): MenuDetailDTO {
        val menuDetailFullPath = "$deliveryApiUrl$menuDetailPath${menuDetailRequest.menuId}?storeId=${menuDetailRequest.storeId}"

        val headers = ExternalHttpApiUtils.getApiHeader(menuDetailRequest.accessToken)

        val httpBody = LinkedMultiValueMap<String, String>()
        httpBody.add("storeId", menuDetailRequest.storeId.toString())

        val request = HttpEntity(httpBody, headers)

        val responseEntity =
            restTemplate.exchange(menuDetailFullPath, HttpMethod.GET, request, MenuDetailResponse::class.java)

        val menuDetailResponse = responseEntity.body ?: throw NotFoundMenuException("메뉴 정보를 찾을 수 없습니다.")

        return MenuDetailDTO(
            storeId = menuDetailResponse.storeId,
            menuId = menuDetailResponse.menuId,
            menuName = menuDetailResponse.menuName,
            menuPrice = menuDetailResponse.price,
            menuMainImageUrl = menuDetailResponse.menuMainImageUrl,
            menuDescription = menuDetailResponse.description,
        )
    }
}