package com.example.todoappexample.viewHolder

import android.content.res.ColorStateList
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappexample.R
import com.example.todoappexample.data.Task
import com.example.todoappexample.data.TasksCategory
import com.example.todoappexample.databinding.ItemTaskBinding

class TasksViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(task: Task) {
        if (task.isSelected) {
            //Como tachar un texto
            binding.tvTask.paintFlags = binding.tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.tvTask.paintFlags =
                binding.tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        binding.tvTask.text = task.name
        binding.ckTask.isChecked = binding.tvTask.isSelected

        val color = when (task.category) {
            TasksCategory.Personal -> R.color.todo_personal_category
            TasksCategory.Business -> R.color.todo_business_category
            TasksCategory.Other -> R.color.todo_other_category
        }
        binding.ckTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(binding.ckTask.context, color)
        )
    }
}