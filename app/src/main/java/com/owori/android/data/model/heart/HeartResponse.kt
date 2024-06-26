package com.owori.android.data.model.heart

import com.google.gson.annotations.SerializedName

data class HeartResponse(
    @SerializedName("isLiked")
    val isLiked: Boolean,
)