package com.hamza.todoapp.ui.ViewPagerFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hamza.todoapp.R
import com.hamza.todoapp.databinding.FragmentViewPagerBinding
import com.hamza.todoapp.ui.CompletedFragment.CompletedFragment
import com.hamza.todoapp.ui.OverDueFragment.OverDueFragment
import com.hamza.todoapp.ui.ToDoFragment.ToDoFragment

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(
            ToDoFragment(),
            CompletedFragment(),
            OverDueFragment()
        )

        val adapter =
            ViewPagerAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)


        binding.viewPager.adapter = adapter


        binding.todoTab.setOnClickListener {
            binding.viewPager.setCurrentItem(0, true)
            it.setBackgroundResource(R.drawable.btn_blue_shape)
            with(binding) {
                todoTab.setTextColor(requireContext().resources.getColor(R.color.white))
                completedTab.setBackgroundResource(R.drawable.btn_white_shape)
                completedTab.setTextColor(requireContext().resources.getColor(R.color.tab_color))
                overdueTab.setBackgroundResource(R.drawable.btn_white_shape)
                overdueTab.setTextColor(requireContext().resources.getColor(R.color.tab_color))
            }
        }

        binding.completedTab.setOnClickListener {
            binding.viewPager.setCurrentItem(1, true)
            it.setBackgroundResource(R.drawable.btn_blue_shape)
            with(binding) {
                completedTab.setTextColor(requireContext().resources.getColor(R.color.white))
                todoTab.setBackgroundResource(R.drawable.btn_white_shape)
                todoTab.setTextColor(R.color.tab_color)
                overdueTab.setBackgroundResource(R.drawable.btn_white_shape)
                overdueTab.setTextColor(R.color.tab_color)
            }
        }

        binding.overdueTab.setOnClickListener {
            binding.viewPager.setCurrentItem(2, true)
            it.setBackgroundResource(R.drawable.btn_blue_shape)
            with(binding) {
                overdueTab.setTextColor(requireContext().resources.getColor(R.color.white))
                completedTab.setBackgroundResource(R.drawable.btn_white_shape)
                completedTab.setTextColor(R.color.tab_color)
                todoTab.setBackgroundResource(R.drawable.btn_white_shape)
                todoTab.setTextColor(R.color.tab_color)
            }


        }


    }
}