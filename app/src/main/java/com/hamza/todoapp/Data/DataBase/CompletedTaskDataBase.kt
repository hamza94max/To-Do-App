package com.hamza.todoapp.Data.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamza.todoapp.Data.Dao.CompletedTaskDao
import com.hamza.todoapp.Data.Models.Task

@Database(
    entities = [Task::class],
    version = 4
)
abstract class CompletedTaskDataBase : RoomDatabase() {


    abstract fun getCompletedTaskDao(): CompletedTaskDao

    companion object {

        @Volatile
        private var instance: CompletedTaskDataBase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CompletedTaskDataBase::class.java,
                "CompletedTask_db"
            )
                .fallbackToDestructiveMigration()
                .build()


    }


}