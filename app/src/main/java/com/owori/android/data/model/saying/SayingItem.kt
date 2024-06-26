package com.owori.android.data.model.saying

import com.google.gson.annotations.SerializedName

data class SayingItem(
    @SerializedName("saying_id")
    val sayingId: String,
    val content: String,
    @SerializedName("member_id")
    val memberId: String,
    @SerializedName("tag_members_id")
    val tagMembersId: List<String>,
    @SerializedName("updated_at")
    val updatedAt: String,
)
