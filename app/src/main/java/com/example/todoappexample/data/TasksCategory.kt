package com.example.todoappexample.data

sealed class TasksCategory(var isSelected: Boolean = true) {
    object Personal : TasksCategory()
    object Business : TasksCategory()
    object Other : TasksCategory()
}