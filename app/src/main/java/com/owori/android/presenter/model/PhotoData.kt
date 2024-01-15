package com.owori.android.presenter.model

data class PhotoData(
    val isThumbnail: Boolean = false,
    val id: Int,
    val imageSrc: String,
)