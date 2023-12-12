package com.fastcampus.deliveryapp.controller.home

import com.fastcampus.deliveryapp.external.cart.CartAdapter
import com.fastcampus.deliveryapp.external.cart.dto.CartItemRequest
import com.fastcampus.deliveryapp.external.customer.CustomerAdapter
import com.fastcampus.deliveryapp.external.customer.CustomerRequest
import com.fastcampus.deliveryapp.external.home.HomeAdapter
import com.fastcampus.deliveryapp.external.home.dto.RecentOrderStore
import com.fastcampus.deliveryapp.external.home.dto.RecentOrderStoreRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val cartAdapter: CartAdapter,
    private val homeAdapter: HomeAdapter,
    private val customerAdapter: CustomerAdapter,
) {
    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    @GetMapping("/display/home", "/")
    fun home(
        @CookieValue(name = "customerId") customerId: Long,
        @CookieValue(name = "access-token") accessToken: String,
        model: Model): String {
        logger.info { ">>> home" }

        val customer = customerAdapter.findByCustomerId(CustomerRequest(customerId, accessToken))
        val cartItemCount = customerId.let { cartAdapter.count(CartItemRequest(customerId, accessToken)) } ?: 0

        val request = RecentOrderStoreRequest(customerId, accessToken)
        val deliveryHomeResponse = homeAdapter.findRecentOrderStores(request)
        if (deliveryHomeResponse == null) {
            model.addAttribute("recentOrderStores", emptyList<RecentOrderStore>())
        } else {
            model.addAttribute("recentOrderStores", deliveryHomeResponse.stores)
        }

        model.addAttribute("currentAddress", customer.address)
        model.addAttribute("cartItemCount", cartItemCount)

        return "/display/home"
    }
}