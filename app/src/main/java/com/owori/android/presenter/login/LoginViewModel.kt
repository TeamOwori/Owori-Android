package com.owori.android.presenter.login

import androidx.lifecycle.LiveData
import com.owori.android.presenter.util.SingleLiveEvent
import com.owori.android.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    private val _callKakaoLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callKakaoLogin: LiveData<Unit> = _callKakaoLogin
    private val _callGoogleLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callGoogleLogin: LiveData<Unit> = _callGoogleLogin

    fun onClickKakaoLogin() {
        _callKakaoLogin.call()
    }

    fun onClickGoogleLogin() {
        _callGoogleLogin.call()
    }
}