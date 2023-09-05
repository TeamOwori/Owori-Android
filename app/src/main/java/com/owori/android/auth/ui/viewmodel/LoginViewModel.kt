package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel

class LoginViewModel : BaseViewModel() {
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