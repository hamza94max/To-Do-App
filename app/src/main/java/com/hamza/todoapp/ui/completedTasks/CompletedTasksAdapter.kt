package com.hamza.todoapp.ui.completedTasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.todoapp.databinding.CompletedTaskItemBinding
import com.hamza.todoapp.domain.models.Task
import javax.inject.Inject

class CompletedTasksAdapter @Inject constructor() :
    RecyclerView.Adapter<CompletedTasksAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: CompletedTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(currentItem: Task) {

            binding.apply {
                taskTitle.text = currentItem.title
                timeTextView.text = currentItem.time
                dateOfDayTextView.text = currentItem.date
                checkbox.isChecked = true
            }
        }
    }

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
    ): CompletedTasksAdapter.TodoViewHolder {
        val view =
            CompletedTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CompletedTasksAdapter.TodoViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.onBind(currentItem)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
