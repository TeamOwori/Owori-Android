package com.owori.android.presenter.model

data class PostData(
    val id: Int,
    val photoList: List<PostPhotoData>?,
    val title: String,
    val contents: String,
    val startDate: String,
    val endDate: String = startDate,
    val likeCount: Int,
    val commentList: List<CommentData>?,
    val author: String
) : java.io.Serializable