package com.owori.android.data.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val token: String,
    @SerializedName("auth_provider")
    val authProvider: AuthProvider
) {
    enum class AuthProvider {
        GOOGLE, KAKAO, APPLE
    }
}