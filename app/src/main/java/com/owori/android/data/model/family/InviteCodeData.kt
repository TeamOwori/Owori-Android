package com.owori.android.data.model.family

import com.google.gson.annotations.SerializedName

data class InviteCodeData(
    @SerializedName("invite_code")
    val inviteCode: String,
)
