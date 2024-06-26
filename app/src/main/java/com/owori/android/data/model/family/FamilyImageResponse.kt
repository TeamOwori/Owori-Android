package com.owori.android.data.model.family

import com.google.gson.annotations.SerializedName

data class FamilyImageResponse(
    @SerializedName("family_image")
    val imageUrl: String,
)
