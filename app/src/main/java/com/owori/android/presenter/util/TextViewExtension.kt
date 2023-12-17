package com.owori.android.presenter.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.owori.android.R

@BindingAdapter("setTitleText")
fun TextView.setTitleText(currentItemIndex: Int) {
    setText(
        when (currentItemIndex) {
            0 -> R.string.onboarding_first_title
            1 -> R.string.onboarding_second_title
            else -> R.string.onboarding_last_title
        }
    )
}

@BindingAdapter("setSubTitleText")
fun TextView.setSubTitleText(currentItemIndex: Int) {
    setText(
        when (currentItemIndex) {
            0 -> R.string.onboarding_first_subtitle
            1 -> R.string.onboarding_second_subtitle
            else -> R.string.onboarding_last_subtitle
        }
    )
}

@BindingAdapter("setNickNameLength")
fun TextView.setNickNameLength(text: String?) {
    text?.let {
        setText(it.length.toString())
        setTextColor(
            when (it.length) {
                0 -> context.getColor(R.color.black)
                else -> context.getColor(R.color.owori_red)
            }
        )
    }
}

