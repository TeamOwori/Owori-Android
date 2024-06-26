package com.owori.android.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comment_id")
    val commentId: String
)
