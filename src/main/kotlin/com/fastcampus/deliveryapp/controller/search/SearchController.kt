package com.fastcampus.deliveryapp.controller.search

import com.fastcampus.deliveryapp.controller.search.dto.SearchRequest
import com.fastcampus.deliveryapp.domain.search.SearchFilter
import com.fastcampus.deliveryapp.service.search.SearchService
import com.fastcampus.deliveryapp.service.searchkeyword.SearchKeywordService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

@Controller
class SearchController(
    private val searchService: SearchService,
    private val searchKeywordService: SearchKeywordService,
) {
    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }

    @GetMapping("/search-form")
    fun searchForm(@RequestParam reviewGradeFilterValue: Long?, model: Model): String{

        model.addAttribute("hitKeywords", searchKeywordService.findHitKeywords())
        model.addAttribute("recentKeywords", searchKeywordService.findRecentKeywords())
        model.addAttribute("reviewGradeFilterValue", reviewGradeFilterValue ?: SearchFilter.DEFAULT_REVIEW_GRADE_FILTER_VALUE)

        return "search/search-form"
    }

    @GetMapping("/search")
    fun searchResult(@ModelAttribute searchRequest: SearchRequest, model: Model): String {
        logger.info { ">>> 검색 요청: ${searchRequest.searchKeyword}" }

        searchKeywordService.addSearchKeyword(searchRequest.searchKeyword)

        val reviewGradeFilterValue = searchRequest.reviewGradeFilterValue ?: SearchFilter.DEFAULT_REVIEW_GRADE_FILTER_VALUE
        val storeDTOS = searchService.search(searchRequest.searchKeyword, reviewGradeFilterValue)

        model.addAttribute("stores", storeDTOS)
        model.addAttribute("keyword", searchRequest.searchKeyword)
        model.addAttribute("reviewGradeFilterValue", reviewGradeFilterValue)

        return "/search/search-result"
    }
}