package vn.sunasterisk.reviewlovestories.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import vn.sunasterisk.reviewlovestories.utils.ConnectivityCheck

abstract class BaseFragment : Fragment() {
    private lateinit var dataBinding: ViewDataBinding
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        dataBinding = inflate<ViewDataBinding>(
            inflater,
            getContentViewId(),
            container,
            false
        )
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    @LayoutRes
    abstract fun getContentViewId(): Int

    fun getDataBinding() = dataBinding
    fun isInternetConnected() = ConnectivityCheck.isConnectedToNetwork()
    fun getFirestoreInstance() = FirebaseFirestore.getInstance()
}
