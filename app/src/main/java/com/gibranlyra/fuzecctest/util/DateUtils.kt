package com.gibranlyra.fuzecctest.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun convertToLocalDateTime(utcString: String): String {
    val deviceTimeZone = ZoneId.systemDefault()
    val deviceDateTime = ZonedDateTime.ofInstant(Instant.parse(utcString), deviceTimeZone)

    val currentBrazilTime = ZonedDateTime.now(deviceTimeZone)


    val longDateFormat =
        DateTimeFormatter.ofPattern("dd.MM.YYYY, HH:mm").let { deviceDateTime.format(it) }
    val smallDateFormat = DateTimeFormatter.ofPattern("HH:mm").let { deviceDateTime.format(it) }

    return when {
        isToday(currentBrazilTime, deviceDateTime) -> "Today, $smallDateFormat"
        else -> longDateFormat
    }
}

private fun isToday(
    currentBrazilTime: ZonedDateTime,
    brazilDateTime: ZonedDateTime
) = currentBrazilTime.dayOfYear == brazilDateTime.dayOfYear

fun getCurrentDateTime(plusDays: Long = 0): String {
    val currentUtcDateTime = LocalDate.now(ZoneOffset.UTC).plusDays(plusDays)

    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentUtcDateTime.format(dateFormat)
}