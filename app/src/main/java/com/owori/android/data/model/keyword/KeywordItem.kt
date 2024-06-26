package com.owori.android.data.model.keyword

import com.google.gson.annotations.SerializedName

data class KeywordItem(
    @SerializedName("keyword_id")
    val keywordId: String,
    val content: String,
)
