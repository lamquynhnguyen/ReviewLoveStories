package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.utils.AuthenticationState

class SignInViewModel(application: Application) : BaseViewModel(application) {
    private val fireBaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState
    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password
    private val _isSentPassReset = MutableLiveData<Boolean>()
    val isSentPassReset: LiveData<Boolean>
        get() = _isSentPassReset
    private val _isEmailInputted = MutableLiveData<Boolean>()
    val isEmailInputted: LiveData<Boolean>
        get() = _isEmailInputted
    private val _isPasswordInputted = MutableLiveData<Boolean>()
    val isPasswordInputted: LiveData<Boolean>
        get() = _isPasswordInputted

    init {
        _authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun onAuthenticate() {
        when {
            email.value.isNullOrEmpty() -> _isEmailInputted.value = false
            password.value.isNullOrEmpty() -> _isPasswordInputted.value = false
            else -> {
                fireBaseAuth.signInWithEmailAndPassword(email.value!!, password.value!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authenticationState.value = if (fireBaseAuth.currentUser!!.isEmailVerified)
                                AuthenticationState.AUTHENTICATED
                            else
                                AuthenticationState.INVALID_AUTHENTICATION
                        } else {
                            _authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
                        }
                    }
                _isEmailInputted.value = true
                _isPasswordInputted.value = true
            }
        }
    }

    fun onForgotPassword() {
        when {
            email.value.isNullOrEmpty() -> _isEmailInputted.value = false
            else -> {
                fireBaseAuth.sendPasswordResetEmail(email.value!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _isSentPassReset.value = true
                        }
                    }
                _isEmailInputted.value = true
                _isSentPassReset.value = false
            }
        }
    }
}

