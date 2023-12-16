package com.owori.android.presenter.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.owori.android.R
import com.owori.android.presenter.model.MyColor

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

@BindingAdapter("setMyColor")
fun ImageView.setMyColor(color: String) {
    when (color) {
        MyColor.RED.colorName -> background = context.getDrawable(R.drawable.icon_my_color_red)
        MyColor.PINK.colorName -> background = context.getDrawable(R.drawable.icon_my_color_pink)
        MyColor.YELLOW.colorName -> background = context.getDrawable(R.drawable.icon_my_color_yellow)
        MyColor.GREEN.colorName -> background = context.getDrawable(R.drawable.icon_my_color_green)
        MyColor.AZURE.colorName -> background = context.getDrawable(R.drawable.icon_my_color_azure)
        MyColor.NAVY.colorName -> background = context.getDrawable(R.drawable.icon_my_color_navy)
        MyColor.PURPLE.colorName -> background = context.getDrawable(R.drawable.icon_my_color_purple)
    }
}

