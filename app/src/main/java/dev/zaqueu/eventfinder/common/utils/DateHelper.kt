package dev.zaqueu.eventfinder.common.utils

import android.text.format.DateFormat
import java.util.*

class DateHelper {
    fun getDateFromTimestamp(time: Long, pattern: String): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return DateFormat.format(pattern, calendar).toString()
    }
}
