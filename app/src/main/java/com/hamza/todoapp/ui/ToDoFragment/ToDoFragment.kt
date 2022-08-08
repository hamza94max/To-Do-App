package com.hamza.todoapp.ui.ToDoFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.Util.DateChecker.getDifferentDays
import com.hamza.todoapp.Util.TimeChecker.checkTime
import com.hamza.todoapp.databinding.FragmentTodoBinding
import com.hamza.todoapp.ui.CompletedFragment.CompletedTaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
class ToDoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: TodoAdapter
    private val todoViewModel: TodoViewModel by viewModels()
    private val completedTaskViewModel: CompletedTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        observeToLiveData()

        todoAdapter.setOnCheckBtnClickListener(object : OnCheckBoxClickListener {
            override fun OnCheckBtnClicked(task: Task) {
                todoViewModel.deleteTask(task)
                completedTaskViewModel.insertCompletedTask(task)
                Toast.makeText(context, "Completed ", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun setUpRecyclerView() {
        binding.todoRecyclerView.apply {
            todoAdapter = TodoAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
    }

    private fun observeToLiveData() {
        todoViewModel.getAllTasks.observe(viewLifecycleOwner) { tasks ->
            todoAdapter.differ.submitList(tasks.reversed().filter {
                getDifferentDays(it.date) > 0 || (getDifferentDays(it.date) == 0 && checkTime(it.time))
            })
            removeNoTaskLayout(tasks)
        }
    }

    private fun removeNoTaskLayout(tasks: List<Task>?) {
        if (tasks!!.isEmpty()) binding.noTaskLayout.visibility = View.VISIBLE
        else binding.noTaskLayout.visibility = View.GONE
    }


}