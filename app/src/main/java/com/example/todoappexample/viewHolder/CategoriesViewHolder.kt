package com.example.todoappexample.viewHolder

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappexample.R
import com.example.todoappexample.data.TasksCategory
import com.example.todoappexample.databinding.ItemTaskCategoryBinding

class CategoriesViewHolder(val binding: ItemTaskCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun render(tasksCategory: TasksCategory, onItemSelected: (Int) -> Unit) {

        val color = if (tasksCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        binding.viewContainer.setCardBackgroundColor(
            ContextCompat.getColor(
                binding.viewContainer.context,
                color
            )
        )

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when (tasksCategory) {
            TasksCategory.Business -> {
                binding.tvCategoryName.text = "Business"
                binding.divider.setBackgroundColor(
                    ContextCompat.getColor(binding.divider.context, R.color.todo_business_category)
                )
            }

            TasksCategory.Other -> {
                binding.tvCategoryName.text = "Personal"
                binding.divider.setBackgroundColor(
                    ContextCompat.getColor(binding.divider.context, R.color.todo_personal_category)
                )
            }

            TasksCategory.Personal -> {
                binding.tvCategoryName.text = "Other"
                binding.divider.setBackgroundColor(
                    ContextCompat.getColor(binding.divider.context, R.color.todo_other_category)
                )
            }
        }
    }
}