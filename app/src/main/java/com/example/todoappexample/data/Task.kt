package com.example.todoappexample.data

data class Task(val name:String, val category: TasksCategory, var isSelected:Boolean = false)