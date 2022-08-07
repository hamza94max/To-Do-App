package com.hamza.todoapp.ui.CompletedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.todoapp.databinding.FragmentCompletedBinding

class CompletedFragment : Fragment() {

    private var _binding: FragmentCompletedBinding? = null
    private val binding get() = _binding!!

    private lateinit var completedTaskAdapter: CompletedTaskAdapter
    private val completedTaskViewModel: CompletedTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompletedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeToLiveData()


    }

    private fun setUpRecyclerView() {
        binding.completedRecyclerView.apply {
            completedTaskAdapter = CompletedTaskAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = completedTaskAdapter
        }
    }

    private fun observeToLiveData() {
        completedTaskViewModel.getAllCompletedTasks.observe(viewLifecycleOwner) { completedtasks ->
            completedTaskAdapter.differ.submitList(completedtasks.asReversed())
        }
    }
}