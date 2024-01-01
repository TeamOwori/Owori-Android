package com.owori.android.presenter.model

data class MyPageData(
    val name: String,
    val birth: String,
    val myColor: MyColorType,
    val profileImage: String?,
    val myStory: Int,
    val likedStory: Int,
    val mailbox: Int,
)

enum class MyColorType(val colorName: String){
    RED("red"), PINK("pink"), YELLOW("yellow"), GREEN("green"), AZURE("azure"), NAVY("navy"), PURPLE("purple")
}