package com.hamza.todoapp.ui.Dialog

import com.hamza.todoapp.Data.Models.Task

interface OnInputListener {
    fun sendInput(input: Task)

}