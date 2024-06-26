package com.owori.android.data.api.image

import com.owori.android.data.model.image.ImageResponse
import com.owori.android.module.DataResult
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {

    // 이미지 업로드 - 이야기
    @Multipart
    @POST("/images")
    fun uploadImages(@Part data: List<MultipartBody.Part>) : DataResult<ImageResponse>
}