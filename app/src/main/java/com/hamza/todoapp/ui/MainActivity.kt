package com.hamza.todoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hamza.todoapp.databinding.ActivityMainBinding
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.ui.addTaskDialog.AddTaskDialog
import com.hamza.todoapp.ui.addTaskDialog.OnInputListener
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnInputListener {

    private lateinit var binding: ActivityMainBinding

    private val tasksViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDateTextView()

        binding.addTaskBtn.setOnClickListener {
            val addTaskDialog = AddTaskDialog()
            addTaskDialog.show(supportFragmentManager, "tag")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDateTextView() {
        AndroidThreeTen.init(this)
        val today: LocalDate = LocalDate.now()
        val dayName = today.dayOfWeek.name
        val year = today.year
        val month = today.month
        binding.dateOfDayTextView.text = "$dayName, $month, $year"
    }

    override fun onTaskAdded(task: Task) {
        tasksViewModel.insertTask(task)
    }

}
