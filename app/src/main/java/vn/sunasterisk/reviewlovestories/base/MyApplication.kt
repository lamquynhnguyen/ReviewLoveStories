package vn.sunasterisk.reviewlovestories.base

import android.app.Application
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
    }

    companion object {
        private var instance: MyApplication? = null
        fun applicationContext() = instance!!.applicationContext
    }
}
