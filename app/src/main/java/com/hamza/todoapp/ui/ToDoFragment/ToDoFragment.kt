package com.hamza.todoapp.ui.ToDoFragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hamza.todoapp.base.BaseFragment
import com.hamza.todoapp.databinding.FragmentTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class ToDoFragment : BaseFragment<FragmentTodoBinding>() {

    @Inject
    lateinit var todoAdapter: TodoAdapter
    private val tasksViewModel: TasksViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTodoBinding
        get() = FragmentTodoBinding::inflate


    override fun prepareView(savedInstanceState: Bundle?) {
        binding.todoRecyclerView.adapter = todoAdapter

        tasksViewModel.getTasks()

        setTasksData()

        todoAdapter.setOnCheckBtnClickListener(object : OnCheckBoxClickListener {
            override fun onCheckBoxClicked(taskID: Int) {
                tasksViewModel.completeTask(taskID)
                showSuccessToast("Completed")
            }
        })
    }

    private fun setTasksData() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tasksViewModel.tasksFlow.collect { tasks ->
                    Log.d("hamzaE", tasks.toString())
                    if (tasks.isEmpty()) showNoTasksView()
                    else {
                        todoAdapter.differ.submitList(tasks)
                        binding.noTaskLayout.isVisible = false
                    }
                }
            }
        }


    }

    private fun showNoTasksView() {
        binding.noTaskLayout.isVisible = true
    }
}
