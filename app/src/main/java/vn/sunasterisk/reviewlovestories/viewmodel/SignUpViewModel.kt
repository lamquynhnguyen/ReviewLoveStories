package vn.sunasterisk.reviewlovestories.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import vn.sunasterisk.reviewlovestories.base.BaseViewModel
import vn.sunasterisk.reviewlovestories.utils.AuthenticationState

class SignUpViewModel(application: Application) : BaseViewModel(application) {
    private val fireBaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val currentUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }
    private val _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState
    private val _username = MutableLiveData<String>()
    val username: MutableLiveData<String>
        get() = _username
    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
        get() = _email
    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password
    private val _isSentEmailVerification = MutableLiveData<Boolean>()
    val isSentEmailVerification: LiveData<Boolean>
        get() = _isSentEmailVerification
    private val _isEmailCorrect = MutableLiveData<Boolean>()
    val isEmailCorrect: LiveData<Boolean>
        get() = _isEmailCorrect
    private val _isPasswordCorrect = MutableLiveData<Boolean>()
    val isPasswordCorrect: LiveData<Boolean>
        get() = _isPasswordCorrect
    private val _isUsernameInputted = MutableLiveData<Boolean>()
    val isUsernameInputted: LiveData<Boolean>
        get() = _isUsernameInputted
    private val _isEmailInputted = MutableLiveData<Boolean>()
    val isEmailInputted: LiveData<Boolean>
        get() = _isEmailInputted
    private val _isPasswordInputted = MutableLiveData<Boolean>()
    val isPasswordInputted: LiveData<Boolean>
        get() = _isPasswordInputted

    init {
        _isUsernameInputted.value = true
        _isEmailInputted.value = true
        _isPasswordInputted.value = true
    }

    private fun sendEmailVerification() {
        currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isSentEmailVerification.value = true
                    if (currentUser!!.isEmailVerified) {
                        _authenticationState.value = AuthenticationState.AUTHENTICATED
                        updateUsername()
                    }
                } else {
                    _isSentEmailVerification.value = false
                }
            }
    }

    private fun updateUsername() {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username.value)
            .build()
        currentUser?.updateProfile(profileUpdates)
    }

    private fun isEmailCheck(email: String?) =
        (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())

    private fun String?.isPasswordCheck() = this?.run{
        isNotEmpty() && length >= MAX_PASSWORD_LENGTH
    }?: false

    fun onSignUp() {
        when {
            username.value.isNullOrEmpty() -> _isUsernameInputted.value = false
            email.value.isNullOrEmpty() -> _isEmailInputted.value = false
            password.value.isNullOrEmpty() -> _isPasswordInputted.value = false
            else -> fireBaseAuth.createUserWithEmailAndPassword(email.value!!, password.value!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sendEmailVerification()
                    } else if (!isEmailCheck(email.value)) {
                        _isEmailCorrect.value = false
                    } else if (!password.value.isPasswordCheck()) {
                        _isPasswordCorrect.value = false
                    } else
                        _authenticationState.value = AuthenticationState.UNAUTHENTICATED
                }
        }
    }
    companion object{
        private const val MAX_PASSWORD_LENGTH = 6
    }
}
