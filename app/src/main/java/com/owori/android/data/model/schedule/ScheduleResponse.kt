package com.owori.android.data.model.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("schedule_id")
    val scheduleId: String,
)
