package com.hamza.todoapp.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamza.todoapp.Util.Priority
import java.io.Serializable

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    val date: String,
    val time: String,
    val priority: Priority,
    var isReminder: Boolean
) : Serializable {
    constructor(
        title: String,
        date: String,
        time: String,
        priority: Priority,
        isReminder: Boolean
    ) :
            this(0, title, date, time, priority, isReminder)
}
