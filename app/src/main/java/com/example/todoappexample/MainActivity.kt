package com.example.todoappexample

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TasksCategory = when (selectedRadioButton.text) {
                    getString(R.string.app_rb_business) -> Business
                    getString(R.string.app_rb_personal) -> Personal
                    else -> Other
                }
                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }
        }


        dialog.show()
    }

    private fun initUI() {
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvTasks.layoutManager = LinearLayoutManager(this)
    }

    private fun initComponents() {
        //Reclycler Categories
        //val myRecyclerCategories = binding.rvCategories
        //myRecyclerCategories.adapter = CategoriesAdapter(categories) {position -> updateCategories(position)}

        val categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
        binding.rvCategories.adapter = categoriesAdapter

        //Recycler Tasks
        //val myRecyclerTask = binding.rvTasks
        //myRecyclerTask.adapter = TasksAdapter(tasks) { onItemSelected(it) }

        val taskAdapter = TasksAdapter(tasks) {onItemSelected(it)}
        binding.rvTasks.adapter = taskAdapter

    }

    private fun onItemSelected(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        binding.rvCategories.adapter?.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks() {
        val selectedCategories: List<TasksCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        (binding.rvTasks.adapter as? TasksAdapter?)?.let {adapter ->
            adapter.tasks = newTasks
            adapter.notifyDataSetChanged()
        }

        //binding.rvTasks.adapter?.notifyDataSetChanged()
    }
}