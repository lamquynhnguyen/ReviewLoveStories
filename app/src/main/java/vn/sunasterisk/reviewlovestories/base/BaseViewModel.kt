package vn.sunasterisk.reviewlovestories.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val firestore = FirebaseFirestore.getInstance()
}
