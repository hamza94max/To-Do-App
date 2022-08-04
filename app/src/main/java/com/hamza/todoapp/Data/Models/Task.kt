package com.hamza.todoapp.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val priority: Int,
    val isReminder: Boolean
) : Serializable
