package vn.sunasterisk.reviewlovestories.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val fireStore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
        .build()
}
