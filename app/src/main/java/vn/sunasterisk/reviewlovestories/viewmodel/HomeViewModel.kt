package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.Query
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.utils.ReviewType

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val _reviewsByTime = MutableLiveData<List<Review>>()
    val reviewsByTime: LiveData<List<Review>>
        get() = _reviewsByTime
    private val _reviewsByHeart = MutableLiveData<List<Review>>()
    val reviewsByHeart: LiveData<List<Review>>
        get() = _reviewsByHeart
    private val _isLoadFavoriteSuccess = MutableLiveData<Boolean>()
    val isLoadFavoriteSuccess: LiveData<Boolean>
        get() = _isLoadFavoriteSuccess
    private val _isLoadLatestSuccess = MutableLiveData<Boolean>()
    val isLoadLatestSuccess: LiveData<Boolean>
        get() = _isLoadLatestSuccess

    init {
        fireStore.firestoreSettings = settings
        getReviewsByHeartLimitTen()
        getReviewsByTimeLimitTen()
    }

    private fun getReviewsByTimeLimitTen() {
        fireStore.collection(ReviewType.REVIEWS)
            .orderBy(ReviewType.UPLOAD_TIME, Query.Direction.DESCENDING)
            .limit(LIMIT_QUERY_REVIEW)
            .get()
            .addOnSuccessListener { reviews ->
                _reviewsByTime.value = reviews.toObjects(Review::class.java)
                _isLoadLatestSuccess.value = true
            }
            .addOnFailureListener {
            }
    }

    private fun getReviewsByHeartLimitTen() {
        fireStore.collection(ReviewType.REVIEWS)
            .orderBy(ReviewType.HEART_COUNT, Query.Direction.DESCENDING)
            .limit(LIMIT_QUERY_REVIEW)
            .get()
            .addOnSuccessListener { reviews ->
                _reviewsByHeart.value = reviews.toObjects(Review::class.java)
                _isLoadFavoriteSuccess.value = true
            }
            .addOnFailureListener {
            }
    }

    companion object {
        private const val LIMIT_QUERY_REVIEW = 5L
    }
}
