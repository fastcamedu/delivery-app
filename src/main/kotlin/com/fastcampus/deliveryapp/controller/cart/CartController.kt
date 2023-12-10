package com.fastcampus.deliveryapp.controller.cart

import com.fastcampus.deliveryapp.external.cart.CartAdapter
import com.fastcampus.deliveryapp.external.cart.dto.CartRequest
import com.fastcampus.deliveryapp.external.customer.CustomerAdapter
import com.fastcampus.deliveryapp.external.customer.CustomerRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CartController(
    private val customerAdapter: CustomerAdapter,
    private val cartAdapter: CartAdapter,
) {
    companion object {
        private val logger = KotlinLogging.logger(CartController::class.java.name)
    }

    @GetMapping("/cart")
    fun getCart(
        @CookieValue(value = "customerId") customerId: Long?,
        @CookieValue(value = "access-token") accessToken: String?,
        model: Model): String {
        if (customerId == null) {
            logger.warn { ">>> Cannot get customerId from Cookies" }
            return "redirect:/customer/login/login"
        }

        val customerResponse = customerAdapter.findByCustomerId(CustomerRequest(customerId, accessToken))
        val cartResponse = cartAdapter.findBy(CartRequest(customerId, accessToken))

        model.addAttribute("customer", customerResponse)
        model.addAttribute("cartItems", cartResponse.cartItems)
        model.addAttribute("canOrder", cartResponse.cartItems.isNotEmpty())

        return "/cart/cart"
    }
}