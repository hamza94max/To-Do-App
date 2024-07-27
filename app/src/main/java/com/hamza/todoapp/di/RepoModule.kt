package com.hamza.todoapp.di

import com.hamza.todoapp.data.local.tasks.TasksDataBase
import com.hamza.todoapp.data.repoImpl.TasksRepoImpl
import com.hamza.todoapp.domain.repo.TasksRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    fun provideTasksRepo(
        tasksDataBase: TasksDataBase,
    ): TasksRepo {
        return TasksRepoImpl(tasksDataBase)
    }


}