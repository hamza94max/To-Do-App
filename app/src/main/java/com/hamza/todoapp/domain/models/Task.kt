package com.hamza.todoapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    val date: String,
    val time: String,
    val priority: TaskPriority,
    val isReminder: Boolean,
    val isCompleted: Boolean
) : Serializable

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH;
}