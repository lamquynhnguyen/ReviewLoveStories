package vn.sunasterisk.reviewlovestories.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import vn.sunasterisk.reviewlovestories.utils.ConnectivityCheck

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
    }

    @LayoutRes
    abstract fun getContentViewId(): Int

    fun isInternetConnected() = ConnectivityCheck.isConnectedToNetwork()
}
