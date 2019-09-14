package vn.sunasterisk.reviewlovestories.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*
import vn.sunasterisk.reviewlovestories.MainActivity
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.base.BaseFragment
import vn.sunasterisk.reviewlovestories.databinding.FragmentSignInBinding
import vn.sunasterisk.reviewlovestories.utils.AuthenticationState
import vn.sunasterisk.reviewlovestories.viewmodel.SignInViewModel

class SignInFragment : BaseFragment() {
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signInFragmentBinding: FragmentSignInBinding
    override val getContentViewId = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelAndDataBinding()
        observeData()
        handleEvents()
    }

    override fun setUpViewModelAndDataBinding() {
        signInViewModel = ViewModelProviders.of(activity as MainActivity).get(SignInViewModel::class.java)
        signInFragmentBinding = getDataBinding() as FragmentSignInBinding
        signInFragmentBinding.signInViewModel = signInViewModel
    }

    override fun observeData() {
        signInViewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AuthenticationState.AUTHENTICATED -> {
                    if (FirebaseAuth.getInstance().currentUser != null)
                        findNavController().navigate(R.id.parentFragment)
                }
                AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
                else -> return@Observer
            }
        })
        signInViewModel.isSentPassReset.observe(this, Observer { isSent ->
            if (isSent) context?.toast(getString(R.string.msg_pass_reset))
        })
        signInViewModel.isEmailInputted.observe(this, Observer { isInputted ->
            if (!isInputted) context?.toast(getString(R.string.msg_email_no_input))
        })
        signInViewModel.isPasswordInputted.observe(this, Observer { isInputted ->
            if (!isInputted) context?.toast(getString(R.string.msg_password_no_input))
        })
    }

    override fun handleEvents() {
        textSignUp.setOnClickListener {
            findNavController().navigate(R.id.actionSignInToSignUp)
        }
    }

    private fun showErrorMessage() {
        context?.toast(getString(R.string.msg_signIn_fail))
    }
}
