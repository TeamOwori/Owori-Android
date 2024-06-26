package com.owori.android.data.model.heart

import com.google.gson.annotations.SerializedName

data class HeartRequest(
    @SerializedName("story_id")
    val storyId: String,
)