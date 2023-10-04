package com.owori.android.auth.ui.viewmodel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.owori.android.common.SingleLiveEvent
import com.owori.android.common.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@ApplicationContext private val context: Context) : BaseViewModel() {
    private val _callKakaoLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callKakaoLogin: LiveData<Unit> = _callKakaoLogin
    private val _callGoogleLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val callGoogleLogin: LiveData<Unit> = _callGoogleLogin
    val userApiClient = UserApiClient()

    fun onClickKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.refreshToken}")
                _callKakaoLogin.call()
            }
        }


        if (userApiClient.isKakaoTalkLoginAvailable(context)) {
            userApiClient.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    userApiClient.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            userApiClient.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun onClickGoogleLogin() {
        _callGoogleLogin.call()
    }


}