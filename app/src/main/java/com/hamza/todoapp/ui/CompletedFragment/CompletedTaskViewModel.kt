package com.hamza.todoapp.ui.CompletedFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.Data.Repository.CompletedTaskRepository
import kotlinx.coroutines.launch

class CompletedTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CompletedTaskRepository = CompletedTaskRepository(application)
    val getAllCompletedTasks: LiveData<List<Task>> = repository.getAllCompletedTasks()

    fun insertCompletedTask(task: Task) {
        viewModelScope.launch {
            repository.insertCompletedTask(task)
        }
    }

    fun deleteCompletedTask(task: Task) {
        viewModelScope.launch {
            repository.deleteCompletedTask(task)
        }
    }

    fun deleteAllCompletedTasks() {
        viewModelScope.launch {
            repository.deleteAllCompletedTasks()
        }
    }
}
