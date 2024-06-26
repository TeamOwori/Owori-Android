package com.owori.android.data.api.comment

import com.owori.android.data.model.comment.CommentEditRequest
import com.owori.android.data.model.comment.CommentPostRequest
import com.owori.android.data.model.comment.CommentResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {

    // 댓글 작성
    @POST("/comments")
    fun postComment(@Body data: CommentPostRequest) : DataResult<CommentResponse>

    // 댓글 수정
    @POST("/comments/update")
    fun editComment(@Body data: CommentEditRequest) : DataResult<CommentResponse>

    // 댓글 삭제
    @DELETE("/comments/{commentId}")
    fun deleteComment(@Path(value = "commentId") id: Int) : DataResult<CommentResponse>
}