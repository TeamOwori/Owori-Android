package com.owori.android.data.api.member

import com.owori.android.data.model.member.SignUpRequest
import com.owori.android.data.model.member.SignUpResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApi {
    @POST("/members/kakao")
    fun kakaoLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/members/google")
    fun googleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>

    @POST("/members/apple")
    fun appleLogin(@Body data: SignUpRequest) : DataResult<SignUpResponse>
}