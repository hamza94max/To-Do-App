package com.hamza.todoapp.ui.completedTasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hamza.todoapp.base.BaseFragment
import com.hamza.todoapp.databinding.FragmentCompletedBinding
import com.hamza.todoapp.ui.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CompletedFragment : BaseFragment<FragmentCompletedBinding>() {

    @Inject
    lateinit var completedTasksAdapter: CompletedTasksAdapter

    private val tasksViewModel: TasksViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCompletedBinding
        get() = FragmentCompletedBinding::inflate


    override fun prepareView(savedInstanceState: Bundle?) {
        setUpRecyclerView()
        setupObservers()
        tasksViewModel.getCompletedTasks()
    }

    private fun setUpRecyclerView() {
        binding.completedRecyclerView.adapter = completedTasksAdapter
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tasksViewModel.completedTasksFlow.collect { tasks ->
                    completedTasksAdapter.differ.submitList(tasks)

                }
            }
        }
    }
}
