package com.owori.android.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentPostRequest(
    @SerializedName("story_id")
    val storyId: String,
    @SerializedName("parent_comment_id")
    val parentCommentId: String?,
    val content: String,
)
