package com.owori.android.presenter.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.owori.android.R

@BindingAdapter("setCircleImageView")
fun ImageView.setCircleImageView(image: Any?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.member_profile)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("setImageView")
fun ImageView.setImageView(image: Any?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.member_profile)
    }
}

