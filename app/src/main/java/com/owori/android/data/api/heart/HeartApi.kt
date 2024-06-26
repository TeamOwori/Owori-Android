package com.owori.android.data.api.heart

import com.owori.android.data.model.heart.HeartRequest
import com.owori.android.data.model.heart.HeartResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.POST

interface HeartApi {

    // 좋아요 생성 취소
    @POST("/hearts")
    fun setLikeState(@Body data: HeartRequest) : DataResult<HeartResponse>
}