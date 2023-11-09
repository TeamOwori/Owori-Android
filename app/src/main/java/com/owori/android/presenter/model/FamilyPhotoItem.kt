package com.owori.android.presenter.model

data class FamilyPhotoItem(
    val viewType: FamilyPhotoViewType,
    val photoData: PhotoData? = null,
) {
    enum class FamilyPhotoViewType {
        PHOTO, ADD_PHOTO
    }
}