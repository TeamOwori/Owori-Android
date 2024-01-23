package com.owori.android.core

import java.text.SimpleDateFormat
import java.util.Date


object DateFormatter {
    private const val DATE_FORMAT_DASH: String = "yyyy-MM-dd"
    fun toDashDateFormat(timestamp: Long): String {
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat(DATE_FORMAT_DASH)

        return dateFormat.format(date)
    }
}