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
    private val _isLoadSuccess = MutableLiveData<Boolean>()
    val isLoadSuccess: LiveData<Boolean>
        get() = _isLoadSuccess

    private fun getReviewsByTime() {
        firestore.collection(ReviewType.REVIEWS)
            .orderBy(ReviewType.UPLOAD_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { reviews ->
                _isLoadSuccess.value = true
                _reviewsByTime.value = reviews.toObjects(Review::class.java)
            }
            .addOnFailureListener {
                _isLoadSuccess.value = false
            }
    }

    private fun getReviewsByHeart() {
        firestore.collection(ReviewType.REVIEWS)
            .orderBy(ReviewType.HEART_COUNT, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { reviews ->
                _isLoadSuccess.value = true
                _reviewsByHeart.value = reviews.toObjects(Review::class.java)
            }
            .addOnFailureListener {
                _isLoadSuccess.value = false
            }
    }
}
