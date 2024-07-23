package com.hamza.todoapp.domain.repo

import com.hamza.todoapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepo {


    suspend fun getTasks(): Flow<List<Task>>

    suspend fun getCompletedTasks(): Flow<List<Task>>

    suspend fun insertTask(task: Task)


    suspend fun completeTask(taskId: Int)

    suspend fun deleteTask(task: Task)


}