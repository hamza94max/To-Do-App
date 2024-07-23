package com.hamza.todoapp.ui.ToDoFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.domain.repo.TasksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksRepo: TasksRepo
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(mutableListOf())
    val tasksFlow: Flow<List<Task>> = _tasks

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tasksRepo.getTasks().collect { tasks ->
                    _tasks.value = tasks
                }
            } catch (e: Exception) {
                Log.e("hamzaERORR", "getTasksError ${e.message}")
            }
        }
    }


    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepo.insertTask(task)
        }
    }

    fun completeTask(taskID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepo.completeTask(taskID)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepo.deleteTask(task)
        }
    }


}
