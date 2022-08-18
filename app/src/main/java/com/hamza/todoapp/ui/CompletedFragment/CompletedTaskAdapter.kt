package com.hamza.todoapp.ui.CompletedFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.databinding.CompletedTaskItemBinding
import javax.inject.Inject

class CompletedTaskAdapter @Inject constructor() :
    RecyclerView.Adapter<CompletedTaskAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: CompletedTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletedTaskAdapter.TodoViewHolder {
        val view =
            CompletedTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CompletedTaskAdapter.TodoViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.binding.taskTitle.text = currentItem.title
        holder.binding.timeTextView.text = currentItem.time
        holder.binding.dateOfDayTextView.text = currentItem.date
        holder.binding.checkbox.isChecked = true
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
