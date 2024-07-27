package com.hamza.todoapp.data.local.tasks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamza.todoapp.domain.models.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TasksDataBase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}
