package com.owori.android.presenter.model

import java.io.Serializable

data class CommentData(
    val id: Int,
    val author: String,
    val contents: String
) : Serializable