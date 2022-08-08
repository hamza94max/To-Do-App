package com.hamza.todoapp.ui.ToDoFragment

import com.hamza.todoapp.Data.Models.Task

interface OnCheckBoxClickListener {
    fun OnCheckBtnClicked(task: Task)
}