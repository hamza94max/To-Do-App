package com.hamza.todoapp.di

import android.app.Application
import androidx.room.Room
import com.hamza.todoapp.data.local.tasks.TasksDataBase
import com.hamza.todoapp.utils.Constants.TASKS_TABLE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideTasksDatabase(app: Application): TasksDataBase =
        Room.databaseBuilder(app, TasksDataBase::class.java, TASKS_TABLE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


}