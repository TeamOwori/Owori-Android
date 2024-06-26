package com.owori.android.data.api.member

import com.owori.android.data.model.member.SignUpRequest
import com.owori.android.data.model.member.SignUpResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApi {
    @POST("/api/v1/members/kakao")
    fun kakaoLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/api/v1/members/google")
    fun googleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/api/v1/members/apple")
    fun appleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>
}