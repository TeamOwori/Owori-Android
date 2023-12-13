package com.owori.android.presenter.model

import android.graphics.drawable.Drawable

data class NoticeData(
    val id: Int,
    val icon: Drawable?,
    val title: String,
    val contents: String,
    val time: String,
)