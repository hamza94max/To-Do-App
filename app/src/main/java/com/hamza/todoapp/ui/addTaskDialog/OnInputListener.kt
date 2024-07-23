package com.hamza.todoapp.ui.addTaskDialog

import com.hamza.todoapp.domain.models.Task

interface OnInputListener {

    fun onTaskAdded(task: Task)

}