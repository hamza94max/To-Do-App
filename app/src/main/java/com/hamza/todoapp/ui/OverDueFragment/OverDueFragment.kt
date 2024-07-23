package com.hamza.todoapp.ui.OverDueFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hamza.todoapp.base.BaseFragment
import com.hamza.todoapp.databinding.FragmentOverDueBinding
import com.hamza.todoapp.ui.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class OverDueFragment : BaseFragment<FragmentOverDueBinding>() {


    @Inject
    lateinit var overDueAdapter: OverDueAdapter
    private val tasksViewModel: TasksViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOverDueBinding
        get() = FragmentOverDueBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        setUpCompletedTasksRecyclerView()
        setUpObservers()
        tasksViewModel.getOverDueTasks()
    }

    private fun setUpCompletedTasksRecyclerView() {
        binding.overDueRecyclerView.adapter = overDueAdapter
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tasksViewModel.overDueTasksFlow.collect { tasks ->
                    overDueAdapter.differ.submitList(tasks)
                }
            }
        }
    }
}
