package com.jay.myportfollio.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun convertTimestampToDate(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp * 1000)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val dateFormat = LocalDate.Format {
        dayOfMonth()
        char('/')
        monthNumber()
        char('/')
        year()
    }
    return dateFormat.format(localDateTime.date)
}