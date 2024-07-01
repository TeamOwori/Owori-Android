package com.owori.android.data.model.schedule

import com.google.gson.annotations.SerializedName

data class SchedulePostRequest(
    val title: String,
    val content: String,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("schedule_type")
    val scheduleType: ScheduleType?,
    val nickname: String?,
    val color: String?,
    @SerializedName("dday_option")
    val dDayOption: Boolean?,
    @SerializedName("alarm_options")
    val alarmOptions: List<String>?,
    @SerializedName("is_mine")
    val isMine: Boolean?,
)

data class ScheduleItem(
    @SerializedName("schedule_id")
    val scheduleId: String?,
    val title: String,
    val content: String,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("schedule_type")
    val scheduleType: ScheduleType?,
    val nickname: String?,
    val color: String?,
    @SerializedName("dday_option")
    val dDayOption: Boolean?,
    @SerializedName("alarm_options")
    val alarmOptions: List<String>?,
    @SerializedName("is_mine")
    val isMine: Boolean?,
)

enum class ScheduleType {
    FAMILY, INDIVIDUAL
}
