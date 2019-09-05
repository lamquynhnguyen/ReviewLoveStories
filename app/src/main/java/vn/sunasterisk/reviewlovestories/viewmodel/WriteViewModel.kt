package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.data.source.local.ReviewRepository
import vn.sunasterisk.reviewlovestories.utils.ReviewType

class WriteViewModel(application: Application) : BaseViewModel(application) {
    private var reviewRepository = ReviewRepository()
    private var localReviews: LiveData<List<Review>>
    private val _isShareSuccess = MutableLiveData<Boolean>()
    val isShareSuccess: LiveData<Boolean>
        get() = _isShareSuccess

    init {
        localReviews = reviewRepository.getReviews()
    }

    private fun insertReviewToFirestore(review: Review) {
        val updateTimeStamp = hashMapOf<String, Any>(ReviewType.UPLOAD_TIME to FieldValue.serverTimestamp())
        val batch = firestore.batch()
        val refReview = firestore.collection(ReviewType.REVIEWS)
            .document("${review.novelName} - ${review.authorName} - ${review.userName}")
        batch.set(refReview, review)
        batch.update(refReview, updateTimeStamp)
        batch.commit()
            .addOnSuccessListener { _isShareSuccess.value = true }
            .addOnFailureListener { _isShareSuccess.value = false }
    }
}
