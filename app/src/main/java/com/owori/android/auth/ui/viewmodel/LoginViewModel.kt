package com.owori.android.auth.ui.viewmodel

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    private val _callKakaoLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callKakaoLogin: LiveData<Unit> = _callKakaoLogin
    private val _callGoogleLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callGoogleLogin: LiveData<Unit> = _callGoogleLogin
    val auth: FirebaseAuth by lazy { Firebase.auth }

    fun onClickKakaoLogin() {
        _callKakaoLogin.call()
    }

    fun onClickGoogleLogin() {
        auth.currentUser?.let {  } ?: _callGoogleLogin.call()
    }
}