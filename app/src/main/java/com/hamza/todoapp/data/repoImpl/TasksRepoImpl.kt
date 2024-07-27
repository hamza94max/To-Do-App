package com.hamza.todoapp.data.repoImpl

import com.hamza.todoapp.data.local.tasks.TasksDataBase
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.domain.repo.TasksRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepoImpl @Inject constructor(
    private val tasksDataBase: TasksDataBase
) : TasksRepo {

    override suspend fun getTasks(currentDate: String): Flow<List<Task>> {
        return tasksDataBase.getTaskDao().getTasks(currentDate)
    }

    override suspend fun getCompletedTasks(): Flow<List<Task>> {
        return tasksDataBase.getTaskDao().getCompletedTasks()
    }

    override suspend fun getOverDueTasks(currentDate: String): Flow<List<Task>> {
        return tasksDataBase.getTaskDao().getOverdueTasks(currentDate)
    }

    override suspend fun insertTask(task: Task) {
        tasksDataBase.getTaskDao().insertTask(task)
    }

    override suspend fun completeTask(taskId: Int) {
        tasksDataBase.getTaskDao().completeTask(taskId)
    }

    override suspend fun deleteTask(task: Task) {
        tasksDataBase.getTaskDao().insertTask(task)
    }


}