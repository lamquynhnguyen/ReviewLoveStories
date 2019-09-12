package vn.sunasterisk.reviewlovestories.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import vn.sunasterisk.reviewlovestories.utils.ConnectivityCheck

abstract class BaseFragment : Fragment() {
    private lateinit var dataBinding: ViewDataBinding
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        dataBinding = inflate<ViewDataBinding>(
            inflater,
            getContentViewId,
            container,
            false
        )
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    abstract val getContentViewId: Int
    abstract fun setUpViewModelAndDataBinding()
    abstract fun observeData()
    abstract fun handleEvents()
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun getDataBinding() = dataBinding
    fun isInternetConnected() = ConnectivityCheck.isConnectedToNetwork()
}
