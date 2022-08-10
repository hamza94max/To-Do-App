package com.hamza.todoapp.ui.ToDoFragment

import com.hamza.todoapp.Data.Models.Task

interface OnCheckBoxClickListener {
    fun OnCheckBoxClicked(task: Task)
}
