package com.hamza.todoapp.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
object DateUtils {


    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        Log.i("hamzaDATE", dateFormat.format(Date()))
        return dateFormat.format(Date())
    }

}
