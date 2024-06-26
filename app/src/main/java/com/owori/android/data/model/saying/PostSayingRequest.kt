package com.owori.android.data.model.saying

import com.google.gson.annotations.SerializedName

data class PostSayingRequest(
    val content: String,
    @SerializedName("tag_members_id")
    val tagMembersId: List<String>
)
