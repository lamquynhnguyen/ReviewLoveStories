package vn.sunasterisk.reviewlovestories.data.source.local

import androidx.lifecycle.LiveData
import vn.sunasterisk.reviewlovestories.base.LocalDataAsyncTask
import vn.sunasterisk.reviewlovestories.base.MyApplication
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.utils.MethodType

class ReviewRepository {
    private var reviewDao: ReviewDao
    private var allReviews: LiveData<List<Review>>

    init {
        val reviewRoomDatabase = ReviewRoomDatabase.getInstance(MyApplication.applicationContext())
        reviewDao = reviewRoomDatabase.reviewDao()
        allReviews = reviewDao.getAllReviews()
    }

    fun getReviews() = allReviews

    fun insertReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.INSERT).execute(review)
    }

    fun updateReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.UPDATE).execute(review)
    }

    fun deleteReview(review: Review) {
        LocalDataAsyncTask(reviewDao, MethodType.DELETE).execute(review)
    }
}
