package com.hamza.todoapp.ui.ToDoFragment

import com.hamza.todoapp.Data.Models.Task

interface OnCheckBtnClickListener {
    fun OnCheckBtnClicked(task: Task)
}