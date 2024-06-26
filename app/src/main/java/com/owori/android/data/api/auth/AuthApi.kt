package com.owori.android.data.api.auth

import com.owori.android.data.model.auth.RefreshResponse
import com.owori.android.module.DataResult
import retrofit2.http.GET

interface AuthApi {
    @GET("/auth/refresh")
    fun getTokenByRefreshToken() : DataResult<RefreshResponse>
}