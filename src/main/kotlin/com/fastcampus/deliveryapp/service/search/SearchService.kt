package com.fastcampus.deliveryapp.service.search

import com.fastcampus.deliveryapp.controller.search.dto.StoreDTO
import com.fastcampus.deliveryapp.external.search.SearchAdapter
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val searchAdapter: SearchAdapter,
) {
    fun search(keyword: String, reviewGradeFilterValue: Int): List<StoreDTO> {
        return searchAdapter.search(keyword, reviewGradeFilterValue)
    }
}