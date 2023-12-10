package com.fastcampus.deliveryapp.controller.checkout

import com.fastcampus.deliveryapp.external.checkout.CheckoutAdapter
import com.fastcampus.deliveryapp.external.checkout.CheckoutDetailRequest
import com.fastcampus.deliveryapp.external.customer.CustomerAdapter
import com.fastcampus.deliveryapp.external.customer.CustomerRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CheckoutController(
    val customerAdapter: CustomerAdapter,
    val checkoutAdapter: CheckoutAdapter,
) {

    @GetMapping("/checkouts")
    fun getCheckout(
        @CookieValue(value = "customerId") customerId: Long,
        @CookieValue(value = "access-token") accessToken: String,
        model: Model
    ): String {
        val customer = customerAdapter.findByCustomerId(CustomerRequest(customerId, accessToken))
        val checkoutDetailResponse = checkoutAdapter.findByCheckoutId(
            CheckoutDetailRequest(customerId = customerId, accessToken = accessToken)
        )
        model.addAttribute("customer", customer)
        model.addAttribute("checkoutId", checkoutDetailResponse.checkoutId)
        model.addAttribute("checkoutItems", checkoutDetailResponse.checkoutItems)
        model.addAttribute("totalAmountForPayment", checkoutDetailResponse.totalAmount)

        return "/checkout/checkout"
    }
}