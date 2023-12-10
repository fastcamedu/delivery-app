package com.fastcampus.deliveryapp.domain.search

class SearchFilter {
    companion object {
        const val DEFAULT_REVIEW_GRADE_FILTER_VALUE = 1

        fun getAvailableReviewGradeValue(reviewGradeFilterValue: Int?) : Int {
            return reviewGradeFilterValue ?: DEFAULT_REVIEW_GRADE_FILTER_VALUE
        }
    }
}