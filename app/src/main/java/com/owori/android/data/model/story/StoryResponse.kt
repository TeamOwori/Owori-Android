package com.owori.android.data.model.story

import com.google.gson.annotations.SerializedName

data class StoryResponse(
    @SerializedName("story_id")
    val storyId: String?,
)

data class StoryListPageResponse(
    val stories: List<StoryItem>?,
    @SerializedName("next_page")
    val nextPage: Int,
    @SerializedName("last_page")
    val lastPage: Int,
)