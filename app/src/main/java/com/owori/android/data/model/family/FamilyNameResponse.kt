package com.owori.android.data.model.family

import com.google.gson.annotations.SerializedName

data class FamilyNameRequest(
    @SerializedName("familyGroupName")
    val familyName: String,
)
