package com.owori.android.data.model.saying

import com.google.gson.annotations.SerializedName

data class SayingResponse(
    @SerializedName("saying_id")
    val sayingId: String
)
