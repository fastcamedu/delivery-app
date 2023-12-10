package com.fastcampus.deliveryapp.controller.orderhistory

import com.fastcampus.deliveryapp.domain.order.OrderStatus
import com.fastcampus.deliveryapp.external.orderhistory.OrderHistoryAdapter
import com.fastcampus.deliveryapp.external.orderhistory.dto.OrderHistoryRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrderHistoryController(
    private val orderHistoryAdapter: OrderHistoryAdapter
) {
    @GetMapping("/order-histories")
    fun index(
        @CookieValue(name = "customerId") customerId: Long,
        @CookieValue(name = "access-token") accessToken: String,
        @RequestParam(name = "orderStatus", defaultValue = "COMPLETE") orderStatus: OrderStatus,
        model: Model
    ): String {
        val orderHistoryRequest = OrderHistoryRequest(
            customerId = customerId,
            accessToken = accessToken,
            orderStatus = orderStatus,
        )
        val orderHistoryResponse = orderHistoryAdapter.list(orderHistoryRequest)
        model.addAttribute("orderStatus", orderStatus)
        model.addAttribute("orderHistories", orderHistoryResponse.orderHistories)

        return "/order-history/order-history"
    }
}