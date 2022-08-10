package com.hamza.todoapp.Data.Repository

import android.app.Application
import com.hamza.todoapp.Data.Dao.CompletedTaskDao
import com.hamza.todoapp.Data.DataBase.CompletedTaskDataBase.Companion.invoke
import com.hamza.todoapp.Data.Models.Task

class CompletedTaskRepository(application: Application) {

    private val dao: CompletedTaskDao by lazy {
        val database = invoke(application)
        database.getCompletedTaskDao()
    }

    fun getAllCompletedTasks() = dao.getAllCompletedTasks()

    suspend fun insertCompletedTask(task: Task) = dao.insertCompletedTask(task)

    suspend fun deleteCompletedTask(task: Task) = dao.deleteCompletedTask(task)

    suspend fun deleteAllCompletedTasks() = dao.deleteAllCompletedTasks()
}
