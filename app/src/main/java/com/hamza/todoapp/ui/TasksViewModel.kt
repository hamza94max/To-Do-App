package com.hamza.todoapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.domain.repo.TasksRepo
import com.hamza.todoapp.utils.DateUtils.getCurrentDate
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
                tasksRepo.getTasks(getCurrentDate()).collect { tasks ->
                    _tasks.value = tasks
                    Log.d("hamzaData", "tasks: $tasks")
                }
            } catch (e: Exception) {
                Log.e("hamzaERORR", "getTasksError ${e.message}")
            }
        }
    }

    private val _completedTasks = MutableStateFlow<List<Task>>(mutableListOf())
    val completedTasksFlow: Flow<List<Task>> = _completedTasks

    fun getCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tasksRepo.getCompletedTasks().collect { tasks ->
                    _completedTasks.value = tasks
                }
            } catch (e: Exception) {
                Log.e("hamzaERORR", "getCompletedTasks: Error ${e.message}")
            }
        }
    }

    private val _overDueTasks = MutableStateFlow<List<Task>>(mutableListOf())
    val overDueTasksFlow: Flow<List<Task>> = _overDueTasks

    fun getOverDueTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tasksRepo.getOverDueTasks(getCurrentDate()).collect { tasks ->
                    _overDueTasks.value = tasks
                    Log.d("hamzaData", "Overdue tasks: $tasks")
                }
            } catch (e: Exception) {
                Log.e("hamzaERORR", "getOverDueTasks: Error ${e.message}")
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
