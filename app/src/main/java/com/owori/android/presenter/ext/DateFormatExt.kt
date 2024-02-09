package com.owori.android.presenter.ext

import android.os.Build
import androidx.annotation.RequiresApi
import com.owori.android.core.Constants
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
fun checkDate(dateString: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern(Constants.OWORI_DATE_FORMAT)
    return try {
        LocalDate.parse(dateString, formatter).isBefore(LocalDate.now())
    } catch (e: DateTimeParseException) {
        false
    }
}

fun String.extractMonthFromDate(): Int {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    val date: Date = dateFormat.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.MONTH) + 1
}

fun String.extractYearFromDate(): Int {
    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
    val date: Date = dateFormat.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR) + 1
}