package com.hamza.todoapp.ui.OverDueFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.todoapp.R
import com.hamza.todoapp.databinding.OverdueItemBinding
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.domain.models.TaskPriority
import javax.inject.Inject

class OverDueAdapter @Inject constructor() :
    RecyclerView.Adapter<OverDueAdapter.OverDueViewHolder>() {

    inner class OverDueViewHolder(val binding: OverdueItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverDueAdapter.OverDueViewHolder {
        val view = OverdueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OverDueViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OverDueAdapter.OverDueViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.binding.taskTitle.text = currentItem.title
        holder.binding.timeTextView.text = currentItem.time
        holder.binding.dateOfDayTextView.text = currentItem.date
        if (!currentItem.isReminder)
            holder.binding.reminderView.visibility = View.GONE

        if (currentItem.priority == TaskPriority.MEDIUM)
            holder.binding.taskPriority.setBackgroundResource(R.drawable.priority_medium)
        else if (currentItem.priority == TaskPriority.HIGH)
            holder.binding.taskPriority.setBackgroundResource(R.drawable.priority_high)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
