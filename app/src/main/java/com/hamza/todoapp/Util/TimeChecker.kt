package com.hamza.todoapp.Util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import org.threeten.bp.LocalTime
import java.text.SimpleDateFormat

object TimeChecker {

    @SuppressLint("SimpleDateFormat")
    private val hourFormat = SimpleDateFormat("HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkTime(time: String): Boolean {

        val currentTime = hourFormat.parse(LocalTime.now().toString())
        val timeTo = hourFormat.parse(time)

        if (timeTo!!.before(currentTime)) return false

        return true
    }
}
