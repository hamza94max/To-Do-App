package com.hamza.todoapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.databinding.ActivityMainBinding
import com.hamza.todoapp.ui.Dialog.AddTaskDialog
import com.hamza.todoapp.ui.Dialog.OnInputListener
import com.hamza.todoapp.ui.ToDoFragment.TodoViewModel
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnInputListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var task: Task

    @Inject
    lateinit var addTaskDialog: AddTaskDialog

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AndroidThreeTen.init(this)
        val today: LocalDate = LocalDate.now()
        val dayName = today.dayOfWeek.name
        val year = today.year
        val month = today.month
        binding.dateOfDayTextView.text = "$dayName, $month, $year"

        binding.insertTaskbtn.setOnClickListener {
            addTaskDialog.show(supportFragmentManager, "tag")
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
