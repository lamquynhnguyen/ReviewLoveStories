package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.data.model.Review
import vn.sunasterisk.reviewlovestories.utils.ReviewType

class SearchViewModel(application: Application) : BaseViewModel(application) {
    private val _reviewsSearch = MutableLiveData<List<Review>>()
    val reviewsSearch: LiveData<List<Review>>
        get() = _reviewsSearch
    private val _isLoadSuccess = MutableLiveData<Boolean>()
    val isLoadSuccess: LiveData<Boolean>
        get() = _isLoadSuccess

    private fun searchReviewByNovelName(novelSearch: String) {
        fireStore.collection(ReviewType.REVIEWS)
            .whereEqualTo(ReviewType.NOVEL_NAME, novelSearch)
            .get()
            .addOnSuccessListener { reviews ->
                _isLoadSuccess.value = true
                _reviewsSearch.value = reviews.toObjects(Review::class.java)
            }
            .addOnFailureListener {
                _isLoadSuccess.value = false
            }
    }

    private fun searchReviewByAuthorName(authorSearch: String) {
        fireStore.collection(ReviewType.REVIEWS)
            .whereEqualTo(ReviewType.AUTHOR_NAME, authorSearch)
            .get()
            .addOnSuccessListener { reviews ->
                _isLoadSuccess.value = true
                _reviewsSearch.value = reviews.toObjects(Review::class.java)
            }
            .addOnFailureListener {
                _isLoadSuccess.value = false
            }
    }
}
