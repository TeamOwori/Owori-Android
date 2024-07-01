package com.owori.android.data.model.story

import com.google.gson.annotations.SerializedName

data class StoryItem(
    @SerializedName("story_id")
    val storyId: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    val title: String,
    val content: String,
    @SerializedName("story_images")
    val storyImages: List<String>?,
    val writer: String?,
    val thumbnail: String?,
    val isMultipleImages: Boolean?,
    @SerializedName("heart_count")
    val heartCount: Int?,
    @SerializedName("comment_count")
    val commentCount: Int?,
    val comments: List<CommentItem>?,
)

data class CommentItem(
    @SerializedName("parent_comment_id")
    val parentCommentId: String,
    @SerializedName("comment_id")
    val commentId: String,
    val comment: String,
    val writer: String,
    @SerializedName("time_before_writing")
    val timeBeforeWriting: String,
    @SerializedName("delete_comment_check")
    val deleteCommentCheck: Boolean,
)

enum class SortType(val value: String) {
    CREATED_AT("created_at"),
    START_DATE("start_date")
}