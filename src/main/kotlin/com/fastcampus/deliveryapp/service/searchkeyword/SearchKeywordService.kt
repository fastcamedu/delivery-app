package com.fastcampus.deliveryapp.service.searchkeyword

import org.springframework.stereotype.Service

@Service
class SearchKeywordService {

    private val hitKeywordMap = HashMap<String, Int>()
    private val recentKeywords = ArrayDeque<String>()

    companion object {
        private const val MAX_RECENT_KEYWORD_COUNT = 5
    }

    fun addSearchKeyword(keyword: String) {
        this.recentKeywords.addFirst(keyword)
        if (hitKeywordMap.containsKey(keyword)) {
            hitKeywordMap[keyword] = hitKeywordMap[keyword]!! + 1
        } else {
            hitKeywordMap[keyword] = 0
        }
    }

    fun findHitKeywords(): List<String> {
        return hitKeywordMap.entries.map { it.key }.toList()
    }

    fun findRecentKeywords(): List<String> {
        return if (recentKeywords.size >= MAX_RECENT_KEYWORD_COUNT) {
            recentKeywords.subList(0, MAX_RECENT_KEYWORD_COUNT)
        } else {
            recentKeywords
        }
    }
}