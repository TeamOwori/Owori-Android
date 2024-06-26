package com.owori.android.data.api.auth

import com.owori.android.data.model.auth.RefreshResponse
import com.owori.android.data.model.member.SignUpRequest
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.GET

interface AuthApi {
    @GET("/api/v1/auth/refresh")
    fun getTokenByRefreshToken(@Body data: SignUpRequest) : DataResult<RefreshResponse>
}