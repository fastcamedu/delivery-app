package com.fastcampus.deliveryapp.controller.search.dto

data class SearchRequest(
    val searchKeyword: String,
    val reviewGradeFilterValue: Int?,
)
