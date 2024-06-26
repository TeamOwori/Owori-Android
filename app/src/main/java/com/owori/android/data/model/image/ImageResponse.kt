package com.owori.android.data.model.image

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("story_images")
    val images: List<String>
)
