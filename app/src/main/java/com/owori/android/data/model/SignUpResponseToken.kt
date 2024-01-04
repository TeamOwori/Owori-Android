package com.owori.android.data.model

import com.google.gson.annotations.SerializedName

data class SignUpResponseToken (
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
)