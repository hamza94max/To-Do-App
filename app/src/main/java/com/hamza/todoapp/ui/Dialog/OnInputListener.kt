package com.hamza.todoapp.ui.Dialog

import com.hamza.todoapp.domain.models.Task

interface OnInputListener {
    fun sendInput(input: Task)

}