package com.hamza.todoapp.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamza.todoapp.Data.Dao.TaskDao
import com.hamza.todoapp.Data.Models.Task

@Database(
    entities = [Task::class],
    version = 4
)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {

        @Volatile
        private var instance: TaskDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TaskDataBase::class.java,
                "Task_db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
