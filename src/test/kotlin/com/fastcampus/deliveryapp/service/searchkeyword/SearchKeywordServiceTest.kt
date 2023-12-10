package com.fastcampus.deliveryapp.service.searchkeyword

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SearchKeywordServiceTest {

    @InjectMocks
    private lateinit var searchKeywordService: SearchKeywordService

    @Test
    fun addSearchKeyword() {
        // given
        val keyword = "치킨"

        // when
        searchKeywordService.addSearchKeyword(keyword)

        // then
        assertThat(searchKeywordService.findRecentKeywords()).isNotEmpty
        assertThat(searchKeywordService.findRecentKeywords().size).isEqualTo(1)
        assertThat(searchKeywordService.findRecentKeywords().first()).isEqualTo(keyword)
    }

    @Test
    fun findHitKeywords() {
        // given
        val hitKeyword1 = "치킨"
        searchKeywordService.addSearchKeyword(hitKeyword1)
        searchKeywordService.addSearchKeyword(hitKeyword1)

        val hitKeyword2 = "피자"
        searchKeywordService.addSearchKeyword(hitKeyword2)

        // when
        val hitKeywords = searchKeywordService.findHitKeywords()

        // then
        assertThat(hitKeywords.size).isEqualTo(2)
        assertThat(hitKeywords[0]).isEqualTo(hitKeyword1)
        assertThat(hitKeywords[1]).isEqualTo(hitKeyword2)
    }
}