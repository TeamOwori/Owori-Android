package com.owori.android.data.api.family

import com.owori.android.data.model.family.FamilyImageResponse
import com.owori.android.data.model.family.FamilyNameRequest
import com.owori.android.data.model.family.InviteCodeData
import com.owori.android.module.DataResult
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FamilyApi {

    // 가족 생성
    @POST("/families")
    fun createFamily(@Body data: FamilyNameRequest) : DataResult<InviteCodeData>

    // 가족 멤버 초대코드로 추가
    @POST("/families/members")
    fun inviteMember(@Body data: InviteCodeData) : DataResult<Any>

    // 가족 그룹 이름 수정
    @POST("/families/group-name")
    fun deleteComment(@Body data: FamilyNameRequest) : DataResult<Any>

    // 가족 이미지 저장
    @Multipart
    @POST("/families/images")
    fun editFamilyThumbnail(@Part image: MultipartBody.Part) : DataResult<FamilyImageResponse>
}