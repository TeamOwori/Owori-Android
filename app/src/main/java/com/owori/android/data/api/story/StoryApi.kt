package com.owori.android.data.api.story

import com.owori.android.data.model.story.StoryItem
import com.owori.android.data.model.story.StoryListPageResponse
import com.owori.android.data.model.story.StoryResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StoryApi {

    // 이야기 등록
    @POST("/stories")
    fun postStory(@Body data: StoryItem): DataResult<StoryResponse>

    // 이야기 수정
    @POST("/stories/update")
    fun editStory(@Body data: StoryItem): DataResult<StoryResponse>

    // 이야기 삭제
    @DELETE("/stories/{storyId}")
    fun deleteStory(@Path(value = "storyId") storyId: String): DataResult<Any>

    // 이야기 전체 조회
    @GET("/stories")
    fun getAllStories(@Query("sort") sortType: String, @Query("page") page: Int, @Query("size") size: Int): DataResult<StoryListPageResponse>

    // 이야기 상세 조회
    @GET("/stories/{storyId}")
    fun getDetailStory(@Path(value = "storyId") storyId: String): DataResult<StoryItem>

    // 이야기 검색
    @GET("/stories/search")
    fun searchStory(@Query("keyword") keyword: String): DataResult<List<StoryItem>>

    // 유저가 작성한 이야기 조회
    @GET("/stories/member")
    fun getStoryWithMember(@Query("sort") sortType: String, @Query("page") page: Int, @Query("size") size: Int): DataResult<StoryListPageResponse>

    // 유저가 좋아한 이야기 조회
    @GET("/stories/heart")
    fun getLikedStoryWithMember(@Query("sort") sortType: String, @Query("page") page: Int, @Query("size") size: Int): DataResult<StoryListPageResponse>
}