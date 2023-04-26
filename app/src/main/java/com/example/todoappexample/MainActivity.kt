package com.example.todoappexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappexample.adapter.CategoriesAdapter
import com.example.todoappexample.adapter.TasksAdapter
import com.example.todoappexample.data.Task
import com.example.todoappexample.data.TasksCategory
import com.example.todoappexample.data.TasksCategory.*
import com.example.todoappexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf(
        Task("PruebaBusiness", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOther", Other)
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initUI()
    }

    private fun initUI() {
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvTasks.layoutManager = LinearLayoutManager(this)
    }

    private fun initComponents() {
        //Reclycler Categories
        val myRecyclerCategories = binding.rvCategories
        myRecyclerCategories.adapter = CategoriesAdapter(categories)

        //Recycler Tasks
        val myRecyclerTask = binding.rvTasks
        myRecyclerTask.adapter = TasksAdapter(tasks)

    }
}