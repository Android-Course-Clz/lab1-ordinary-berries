package me.ordinary_berries.lab1.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

fun Instant.intoGeneralTextTime(): String {
    val dateTime = LocalDateTime.ofInstant(this, ZoneId.systemDefault())

    val monthName = dateTime.month.name.take(3).lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val dayNumber = dateTime.dayOfMonth.toString().padStart(2, '0')
    val year = dateTime.year

    return "$monthName $dayNumber, $year"
}