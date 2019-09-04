package vn.sunasterisk.reviewlovestories.data.source.local

import vn.sunasterisk.reviewlovestories.base.LocalDataAsyncTask
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.data.source.ReviewDataSource
import vn.sunasterisk.reviewlovestories.utils.MethodType

class LocalDataSource(private val reviewDao: ReviewDao) : ReviewDataSource.Local {
    override fun getReviews(callback: ReviewDataSource.GetReviewsCallback) {
        val reviews = reviewDao.getAllReviews()
        if (reviews.isEmpty()) callback.onSuccess(reviews)
        else callback.onFailure()
    }

    override fun insertReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.INSERT).execute(review)
    }

    override fun updateReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.UPDATE).execute(review)
    }

    override fun deleteReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.DELETE).execute(review)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null
        @JvmStatic
        fun getInstance(reviewDao: ReviewDao): LocalDataSource =
            INSTANCE ?: synchronized(LocalDataSource::javaClass) {
                INSTANCE ?: LocalDataSource(reviewDao).also { INSTANCE = it }
            }

        fun clearInstance() {
            INSTANCE = null
        }
    }
}
