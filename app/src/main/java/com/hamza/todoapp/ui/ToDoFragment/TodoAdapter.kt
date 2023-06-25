package com.hamza.todoapp.ui.ToDoFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamza.todoapp.Data.Models.Priority
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.R
import com.hamza.todoapp.databinding.TaskItemBinding
import javax.inject.Inject

class TodoAdapter @Inject constructor() : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(),
    OnCheckBoxClickListener {

    inner class TodoViewHolder(val binding: TaskItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val view = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.binding.taskTitle.text = currentItem.title
        holder.binding.timeTextView.text = currentItem.time
        holder.binding.dateOfDayTextView.text = currentItem.date
        if (!currentItem.isReminder)
            holder.binding.reminderView.visibility = View.GONE

        if (currentItem.priority == Priority.MEDIUM)
            holder.binding.taskPriority.setBackgroundResource(R.drawable.priority_medium)
        else if (currentItem.priority == Priority.HIGH)
            holder.binding.taskPriority.setBackgroundResource(R.drawable.priority_high)

        holder.binding.checkbox.setOnClickListener {
            listener!!.OnCheckBoxClicked(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnCheckBtnClickListener(listener: OnCheckBoxClickListener) {
        TodoAdapter.listener = listener
    }

    override fun OnCheckBoxClicked(task: Task) {}

    companion object {
        var listener: OnCheckBoxClickListener? = null
    }
}
