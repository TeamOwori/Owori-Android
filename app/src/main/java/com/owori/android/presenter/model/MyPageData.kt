package com.owori.android.presenter.model

data class MyPageData(
    val name: String,
    val birth: String,
    val myColor: MyColor,
    val profileImage: String?,
)

enum class MyColor(val colorName: String){
    RED("red"), PINK("pink"), YELLOW("yellow"), GREEN("green"), AZURE("azure"), NAVY("navy"), PURPLE("purple")
}