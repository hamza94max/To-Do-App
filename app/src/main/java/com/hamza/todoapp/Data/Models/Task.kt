package com.hamza.todoapp.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamza.todoapp.Util.Priority
import java.io.Serializable

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val date: String,
    val time: String,
    val priority: Priority,
    val isReminder: Boolean
) : Serializable
