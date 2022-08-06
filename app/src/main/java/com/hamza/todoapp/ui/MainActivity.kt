package com.hamza.todoapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.databinding.ActivityMainBinding
import com.hamza.todoapp.ui.ToDoFragment.TodoViewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity(), TaskDialog.OnInputListener {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var task: Task

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.deleteAllTasks()

        val dayName = LocalDate.now().dayOfWeek.name
        val year = LocalDate.now().year
        val month = LocalDate.now().month
        binding.dateOfDayTextView.text = "$dayName, $month, $year"

        binding.insertTaskbtn.setOnClickListener {
            val taskDialog: TaskDialog = TaskDialog()
            taskDialog.show(supportFragmentManager, "tag")
        }


    }

    override fun sendInput(input: Task) {
        task = input
        addTask()
    }

    private fun addTask() {
        viewModel.insertTask(task)
    }


}