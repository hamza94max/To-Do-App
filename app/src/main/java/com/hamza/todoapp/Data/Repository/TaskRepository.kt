package com.hamza.todoapp.Data.Repository

import android.app.Application
import com.hamza.todoapp.Data.Dao.TaskDao
import com.hamza.todoapp.Data.DataBase.TaskDataBase.Companion.invoke
import com.hamza.todoapp.Data.Models.Task

class TaskRepository(application: Application) {

    private val dao: TaskDao by lazy {
        val database = invoke(application)
        database.getTaskDao()
    }

    fun getAllTasks() = dao.getAllTasks()

    suspend fun insertTask(task: Task) = dao.insertTask(task)

    suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    suspend fun deleteAllTasks() = dao.deleteAllTasks()


}