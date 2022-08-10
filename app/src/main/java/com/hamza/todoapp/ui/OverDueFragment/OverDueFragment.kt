package com.hamza.todoapp.ui.OverDueFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.todoapp.Util.DateChecker.getDifferentDays
import com.hamza.todoapp.Util.TimeChecker.checkTime
import com.hamza.todoapp.databinding.FragmentOverDueBinding
import com.hamza.todoapp.ui.ToDoFragment.TodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
class OverDueFragment : Fragment() {

    private var _binding: FragmentOverDueBinding? = null
    private val binding get() = _binding!!

    private lateinit var overDueAdapter: OverDueAdapter
    private val overDueViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverDueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        observeToLiveData()
    }

    private fun setUpRecyclerView() {
        binding.overDueRecyclerView.apply {
            overDueAdapter = OverDueAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = overDueAdapter
        }
    }

    private fun observeToLiveData() {
        overDueViewModel.getAllTasks.observe(viewLifecycleOwner) { tasks ->
            overDueAdapter.differ.submitList(
                tasks.reversed().filter {
                    getDifferentDays(it.date) < 0 || (getDifferentDays(it.date) == 0 && !checkTime(
                        it.time
                    ))
                }
            )
        }
    }
}
