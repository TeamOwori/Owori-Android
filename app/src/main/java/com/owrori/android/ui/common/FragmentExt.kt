package com.owrori.android.ui.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(res: Int) {
    findNavController().navigate(res)
}