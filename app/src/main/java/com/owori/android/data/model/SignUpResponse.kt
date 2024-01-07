package com.owori.android.data.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("member_name")
    val memberId: String,
    @SerializedName("is_service_member")
    val isMember: Boolean,
    @SerializedName("jwt_token")
    val token: SignUpResponseToken,
) {

}