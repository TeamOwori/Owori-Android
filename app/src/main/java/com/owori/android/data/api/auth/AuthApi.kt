package com.owori.android.data.api.auth

import com.owori.android.data.model.SignUpRequest
import com.owori.android.data.model.SignUpResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.POST

/*
* Created by JJJoonngg
*/

interface AuthApi {
    @POST("/members/kakao")
    fun kakaoLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/members/google")
    fun googleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/members/apple")
    fun appleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>
}