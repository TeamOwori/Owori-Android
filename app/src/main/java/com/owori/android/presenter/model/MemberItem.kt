package com.owori.android.presenter.model

import android.graphics.drawable.Drawable

data class MemberItem (
    val id: Int,
    val name: String,
    val profileImage: String,
    val emotionDrawable: Drawable?,
)