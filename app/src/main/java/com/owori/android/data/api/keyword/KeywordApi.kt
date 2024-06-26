package com.owori.android.data.api.keyword

import com.owori.android.data.model.keyword.KeywordItem
import com.owori.android.module.DataResult
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface KeywordApi {

    // 최신 검색어 조회
    @GET("/keywords")
    fun getRecentKeyword() : DataResult<List<KeywordItem>>

    // 최신 검색어 단일 삭제
    @DELETE("/keywords/{keywordId}")
    fun deleteKeyword(@Path(value = "keywordId") keyword: String): DataResult<Any>

    // 최신 검색어 전체 삭제
    @DELETE("/keywords")
    fun deleteAllKeyword(): DataResult<Any>
}