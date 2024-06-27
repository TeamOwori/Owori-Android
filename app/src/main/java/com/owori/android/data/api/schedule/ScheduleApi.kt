package com.owori.android.data.api.schedule

import com.owori.android.data.model.schedule.ScheduleItem
import com.owori.android.data.model.schedule.SchedulePostRequest
import com.owori.android.data.model.schedule.ScheduleResponse
import com.owori.android.module.DataResult
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

    // 일정 등록
    @POST("/schedule")
    fun registerSchedule(@Body data: SchedulePostRequest): DataResult<ScheduleResponse>

    // 일정 수정
    @POST("/schedule/update")
    fun editSchedule(@Body data: ScheduleItem): DataResult<ScheduleResponse>

    // 일정 삭제
    @DELETE("/schedule/{scheduleId}")
    fun deleteSchedule(@Path(value = "scheduleId") id: String): DataResult<Any>

    // 일정 월별 조회
    @GET("/schedule/month")
    fun getMonthlySchedule(@Query("year_month")yearMonth: String): DataResult<List<ScheduleItem>>

    // 가족별 디데이 조회
    @GET("/schedule/dday")
    fun getMonthlyDDay(): DataResult<List<ScheduleItem>>
}