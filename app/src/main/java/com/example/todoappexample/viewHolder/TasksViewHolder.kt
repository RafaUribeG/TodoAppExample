package com.example.todoappexample.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.todoappexample.data.Task
import com.example.todoappexample.databinding.ItemTaskBinding

class TasksViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(task: Task) {
        binding.tvTask.text = task.name
    }
}