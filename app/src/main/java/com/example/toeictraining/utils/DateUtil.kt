package com.example.toeictraining.utils

import android.util.Log
import com.example.toeictraining.ui.fragments.test.score.ScoreTestFragment.Companion.TAG
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtil {

    private const val DATE_FORMAT = "dd/MM/yyyy"
    private const val HOUR_MINUTE_FORMAT = "mm:ss"
    const val TIME_FORMAT = "%02d:%02d"
    val dateFormater = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    fun getCurrentTime(): String =
        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(System.currentTimeMillis()))

    fun secondsToStringTime(seconds: Int): String {
        var temp = seconds
        var hour = 0
        var minute = 0
        var second = 0

        if (temp >= 3600) {
            hour = temp / (3600)
            temp -= hour * 3600
        }
        if (temp >= 60) {
            minute = temp / 60
            second = temp - minute * 60
        } else {
            second = temp
        }
        var result = ""
        if (hour > 0) result += "0$hour:"

        if (minute > 0) {
            result += if (minute < 10) "0$minute:" else "$minute:"
        } else {
            result += "00:"
        }
        if (second > 0) {
            result += if (second < 10) "0$second" else "$second"
        } else {
            result += "00"
        }
        return result
    }

    fun getDelayMinutes(time: String): Long {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        var hour: Int
        var minute: Int
        time.split(':').let { times ->
            hour = Integer.parseInt(times[0])
            minute = Integer.parseInt(times[1])
        }

        val totalCurrentMinutes = currentHour * 60 + currentMinute
        val totalMinutes = hour * 60 + minute

        return if (totalCurrentMinutes > totalMinutes) {
            (24 * 60 - totalCurrentMinutes + totalMinutes).toLong()
        } else {
            (totalMinutes - totalCurrentMinutes).toLong()
        }.also {
            Log.d(TAG, "DELAY MINUTES $it")
        }
    }

    fun getDaysBetween(start: String, end: String): Long {
        val startMillis = dateFormater.parse(start)?.time ?: System.currentTimeMillis()
        val endMillis = dateFormater.parse(end)?.time ?: System.currentTimeMillis()
        return TimeUnit.MILLISECONDS.toDays(endMillis - startMillis)
    }

}


