package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.utils.ReviewType

class GenreViewModel(application: Application) : BaseViewModel(application) {
    private val _isLoadSuccess = MutableLiveData<Boolean>()
    val isLoadSuccess: LiveData<Boolean>
        get() = _isLoadSuccess
    private val _reviewsByGenre = MutableLiveData<List<Review>>()
    val reviewsByGenre: LiveData<List<Review>>
        get() = _reviewsByGenre

    private fun getReviewsByGenre(genreName: String) {
        firestore.collection(ReviewType.REVIEWS)
            .whereArrayContains(ReviewType.GENRES, genreName)
            .get()
            .addOnSuccessListener { reviews ->
                _isLoadSuccess.value = true
                _reviewsByGenre.value = reviews.toObjects(Review::class.java)
            }
            .addOnFailureListener {
                _isLoadSuccess.value = false
            }
    }
}
