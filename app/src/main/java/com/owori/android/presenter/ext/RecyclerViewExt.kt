package com.owori.android.presenter.ext

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter


@BindingAdapter("marginImageAdder")
fun View.marginImageAdder(isFull: Boolean) {
    with(this) {
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val marginStart = if (isFull) 30.toDp() else 114.toDp()
            (layoutParams as ViewGroup.MarginLayoutParams).setMargins(marginStart, 8.toDp(), 30.toDp(), 0)
            requestLayout()
        }
    }
}

fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()