package com.owori.android.presenter.ext

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.owori.android.R
import com.owori.android.presenter.model.ColorStatus
import com.owori.android.presenter.model.ColorStatus.ABLE
import com.owori.android.presenter.model.ColorStatus.CHECKED
import com.owori.android.presenter.model.ColorStatus.DISABLED
import com.owori.android.presenter.model.MyColorType
import com.owori.android.presenter.model.MyColorType.AZURE
import com.owori.android.presenter.model.MyColorType.GREEN
import com.owori.android.presenter.model.MyColorType.NAVY
import com.owori.android.presenter.model.MyColorType.PINK
import com.owori.android.presenter.model.MyColorType.PURPLE
import com.owori.android.presenter.model.MyColorType.RED
import com.owori.android.presenter.model.MyColorType.YELLOW

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
fun ImageView.setMyColor(color: MyColorType) {
    background = when (color) {
        RED -> getDrawable(context, R.drawable.icon_my_color_red)
        PINK -> getDrawable(context, R.drawable.icon_my_color_pink)
        YELLOW -> getDrawable(context, R.drawable.icon_my_color_yellow)
        GREEN -> getDrawable(context, R.drawable.icon_my_color_green)
        AZURE -> getDrawable(context, R.drawable.icon_my_color_azure)
        NAVY -> getDrawable(context, R.drawable.icon_my_color_navy)
        PURPLE -> getDrawable(context, R.drawable.icon_my_color_purple)
    }

}

@BindingAdapter("setRedColor")
fun ImageView.setRedColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_red)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_red_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_red_checked)
    }
}

@BindingAdapter("setPinkColor")
fun ImageView.setPinkColor(status: ColorStatus) {
    Log.d("status", "${status}")
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_pink)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_pink_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_pink_checked)
    }
}

@BindingAdapter("setYellowColor")
fun ImageView.setYellowColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_yellow)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_yellow_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_yellow_checked)
    }
}

@BindingAdapter("setGreenColor")
fun ImageView.setGreenColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_green)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_green_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_green_checked)
    }
}

@BindingAdapter("setAzureColor")
fun ImageView.setAzureColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_azure)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_azure_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_azure_checked)
    }
}

@BindingAdapter("setNavyColor")
fun ImageView.setNavyColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_navy)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_navy_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_navy_checked)
    }
}

@BindingAdapter("setPurpleColor")
fun ImageView.setPurpleColor(status: ColorStatus) {
    background = when (status) {
        ABLE -> getDrawable(context, R.drawable.icon_my_color_purple)
        DISABLED -> getDrawable(context, R.drawable.icon_my_color_purple_blocked)
        CHECKED -> getDrawable(context, R.drawable.icon_my_color_purple_checked)
    }
}


