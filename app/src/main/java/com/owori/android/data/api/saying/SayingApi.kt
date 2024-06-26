package com.owori.android.data.api.saying

import com.owori.android.data.model.saying.EditSayingRequest
import com.owori.android.data.model.saying.PostSayingRequest
import com.owori.android.data.model.saying.SayingItem
import com.owori.android.data.model.saying.SayingResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SayingApi {

    // 서로에게 한마디 등록
    @POST("/saying")
    fun postSaying(@Body data: PostSayingRequest): DataResult<SayingResponse>

    // 서로에게 한마디 수정
    @POST("/saying/update")
    fun editSaying(@Body data: EditSayingRequest): DataResult<SayingResponse>

    // 서로에게 한마디 삭제
    @DELETE("/saying/{sayingId}")
    fun deleteSaying(@Path(value = "sayingId") sayingId: String): DataResult<Any>

    // 서로에게 한마디 가족 단위 조회
    @GET("/saying")
    fun getFamilySaying(): DataResult<List<SayingItem>>
}