package com.hamza.todoapp.ui.viewPager

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.hamza.todoapp.R
import com.hamza.todoapp.base.BaseFragment
import com.hamza.todoapp.databinding.FragmentViewPagerBinding
import com.hamza.todoapp.ui.OverDueFragment.OverDueFragment
import com.hamza.todoapp.ui.completedTasks.CompletedFragment
import com.hamza.todoapp.ui.toDoList.ToDoFragment
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("ResourceAsColor")
@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentViewPagerBinding
        get() = FragmentViewPagerBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        val fragments = listOf(
            ToDoFragment(),
            CompletedFragment(),
            OverDueFragment()
        )

        val adapter =
            ViewPagerAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTabBackground(position)
            }
        })

        binding.todoTab.setOnClickListener { selectTab(0) }
        binding.completedTab.setOnClickListener { selectTab(1) }
        binding.overdueTab.setOnClickListener { selectTab(2) }

        // Set initial tab
        selectTab(0)
    }

    private fun selectTab(position: Int) {
        binding.viewPager.setCurrentItem(position, true)
        updateTabBackground(position)
    }

    private fun updateTabBackground(position: Int) {
        with(binding) {
            listOf(todoTab, completedTab, overdueTab).forEachIndexed { index, tab ->
                if (index == position) {
                    tab.setBackgroundResource(R.drawable.btn_blue_shape)
                    tab.setTextColor(requireContext().resources.getColor(R.color.white))
                } else {
                    tab.setBackgroundResource(R.drawable.btn_white_shape)
                    tab.setTextColor(requireContext().resources.getColor(R.color.tab_color))
                }
            }
        }
    }
}


