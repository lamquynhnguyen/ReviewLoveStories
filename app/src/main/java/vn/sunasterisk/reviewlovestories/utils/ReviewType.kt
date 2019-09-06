package vn.sunasterisk.reviewlovestories.utils

import androidx.annotation.StringDef

@StringDef(
    ReviewType.REVIEW,
    ReviewType.REVIEWS,
    ReviewType.NOVEL_NAME,
    ReviewType.AUTHOR_NAME,
    ReviewType.USER_NAME,
    ReviewType.UPLOAD_TIME,
    ReviewType.HEART_COUNT,
    ReviewType.GENRES
)
annotation class ReviewType {
    companion object {
        const val REVIEW = "review"
        const val REVIEWS = "reviews"
        const val NOVEL_NAME = "novelName"
        const val AUTHOR_NAME = "authorName"
        const val USER_NAME = "userName"
        const val UPLOAD_TIME = "uploadTime"
        const val HEART_COUNT = "heartCount"
        const val GENRES = "genres"
    }
}
