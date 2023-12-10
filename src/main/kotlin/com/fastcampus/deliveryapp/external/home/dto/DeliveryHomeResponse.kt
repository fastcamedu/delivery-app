package com.fastcampus.deliveryapp.external.home.dto

import com.fastcampus.deliveryapp.controller.search.dto.StoreDTO

data class DeliveryHomeResponse(
    val catalogs: List<CategoryDTO>,
    val stores: List<StoreDTO>,
)