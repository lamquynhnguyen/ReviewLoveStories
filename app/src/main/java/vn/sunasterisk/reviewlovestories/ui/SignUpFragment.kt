package vn.sunasterisk.reviewlovestories.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_up.*
import vn.sunasterisk.reviewlovestories.R
import vn.sunasterisk.reviewlovestories.base.BaseFragment
import vn.sunasterisk.reviewlovestories.databinding.FragmentSignUpBinding
import vn.sunasterisk.reviewlovestories.utils.AuthenticationState
import vn.sunasterisk.reviewlovestories.viewmodel.SignUpViewModel

class SignUpFragment : BaseFragment() {
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var signUpBindingFragment: FragmentSignUpBinding
    override val getContentViewId = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelAndDataBinding()
        observeData()
        handleEvents()
    }

    override fun setUpViewModelAndDataBinding() {
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        signUpBindingFragment = getDataBinding() as FragmentSignUpBinding
        signUpBindingFragment.signUpViewModel = signUpViewModel
    }

    override fun observeData() {
        signUpViewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AuthenticationState.AUTHENTICATED -> {
                    findNavController().navigate(R.id.parentFragment)
                }
                AuthenticationState.UNAUTHENTICATED -> showErrorMessage()
                else -> return@Observer
            }
        })
        signUpViewModel.isSentEmailVerification.observe(this, Observer { isSent ->
            if (isSent) {
                findNavController().navigate(R.id.signInFragment)
                context?.toast(getString(R.string.msg_email_sent))
            }
        })
        signUpViewModel.isEmailCorrect.observe(this, Observer { isEmailCorrect ->
            if (!isEmailCorrect) context?.toast(getString(R.string.msg_incorrect_email))
        })
        signUpViewModel.isPasswordCorrect.observe(this, Observer { isPasswordCorrect ->
            if (!isPasswordCorrect) context?.toast(getString(R.string.msg_incorrect_password))
        })
        signUpViewModel.isUsernameInputted.observe(this, Observer { isInputted ->
            if (!isInputted) context?.toast(getString(R.string.msg_username_no_input))
        })
        signUpViewModel.isEmailInputted.observe(this, Observer { isInputted ->
            if (!isInputted) context?.toast(getString(R.string.msg_email_no_input))
        })
        signUpViewModel.isPasswordInputted.observe(this, Observer { isInputted ->
            if (!isInputted) context?.toast(getString(R.string.msg_password_no_input))
        })
    }

    override fun handleEvents() {
        textBackToSignIn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showErrorMessage() {
        context?.toast(getString(R.string.msg_signUp_fail))
    }
}
