package com.hamza.todoapp.ui.toDoList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hamza.todoapp.base.BaseFragment
import com.hamza.todoapp.databinding.FragmentTodoBinding
import com.hamza.todoapp.ui.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ToDoFragment : BaseFragment<FragmentTodoBinding>() {

    @Inject
    lateinit var todoListAdapter: TodoAdapter

    private val tasksViewModel: TasksViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTodoBinding
        get() = FragmentTodoBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

        setupRecyclerView()
        setupObservers()
        setupListeners()
        tasksViewModel.getTasks()

    }

    private fun setupRecyclerView() {
        binding.todoRecyclerView.adapter = todoListAdapter
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tasksViewModel.tasksFlow.collect { tasks ->
                    Log.d("hamzaE", tasks.toString())

                    setNoTasksViewVisibility(tasks.isEmpty())
                    todoListAdapter.differ.submitList(tasks)

                }
            }
        }
    }

    private fun setupListeners() {
        todoListAdapter.setOnCheckBtnClickListener(object : OnCheckBoxClickListener {
            override fun onCheckBoxClicked(taskID: Int) {
                tasksViewModel.completeTask(taskID)
                showSuccessToast("Completed")
            }
        })
    }

    private fun setNoTasksViewVisibility(isVisible: Boolean) {
        binding.noTaskLayout.isVisible = isVisible
    }

}
