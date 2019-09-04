package vn.sunasterisk.reviewlovestories.data.source

import vn.sunasterisk.reviewlovestories.data.model.Review

interface ReviewDataSource {
    interface GetReviewsCallback {
        fun onSuccess(reviews: List<Review>)
        fun onFailure()
    }

    interface Local {
        fun getReviews(callback: GetReviewsCallback)
        fun insertReview(review: Review)
        fun updateReview(review: Review)
        fun deleteReview(review: Review)
    }
}
