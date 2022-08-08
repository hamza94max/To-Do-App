package com.hamza.todoapp.Util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
object DateChecker {

    private val sdf = SimpleDateFormat("dd/MM/yyyy")

    @SuppressLint("SimpleDateFormat")
    fun getDifferentDays(date: String): Int {
        val selectedDate: Date = sdf.parse(date) as Date

        val diff: Long = Date().time - selectedDate.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        if (isEquals(date))
            return -days.toInt()

        return days.toInt()
    }

    @SuppressLint("SimpleDateFormat")
    private fun isEquals(selectedDate: String): Boolean {

        var isEquals = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val strDate: Date = sdf.parse(selectedDate) as Date
            isEquals = Date().after(strDate)
        }
        return !isEquals
    }


}