package com.owori.android.data.model.member

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val token: String,
    @SerializedName("auth_provider")
    val authProvider: AuthProvider
)