package vn.sunasterisk.reviewlovestories.utils

import android.content.Context
import android.net.ConnectivityManager
import vn.sunasterisk.reviewlovestories.base.MyApplication

class ConnectivityCheck {
    companion object {
        fun isConnectedToNetwork(): Boolean {
            var isConnected = false
            val connectivityManager = MyApplication.applicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = (activeNetwork != null) && (activeNetwork.isConnected())
            return isConnected
        }
    }
}
