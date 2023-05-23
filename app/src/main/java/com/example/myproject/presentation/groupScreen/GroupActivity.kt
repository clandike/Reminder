package com.example.myproject.presentation.groupScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.data.local.model.Task

class GroupActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelGroupActivity
    lateinit var adapter: AdapterForGroupActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_activity)

        viewModel = ViewModelProvider(this).get(ViewModelGroupActivity::class.java)

        adapter = AdapterForGroupActivity { position ->
            if (viewModel.nameGroup == "CompletedTasks") {
                viewModel.deleteMainSreenTask(true, position)
            } else {
                viewModel.deleteMainSreenTask(false, position)

            }
        }
        adapter.setTaskList(viewModel.getGroupTasks())

        val nameGroup = findViewById<TextView>(R.id.tvNameGroup)
        when (viewModel.nameGroup) {
            "Work" -> {
                nameGroup.text = "Work tasks:"
            }
            "Study" -> {
                nameGroup.text = "Study tasks:"
            }
            "CompletedTasks" -> {
                nameGroup.text = "Completed tasks:"
            }
            "TODAY_TASKS@" -> {
                nameGroup.text = "Today tasks:"
            }
            "LATER_TASKS@" -> {
                nameGroup.text = "To do later tasks:"
            }

        }

        val rv = findViewById<RecyclerView>(R.id.rvGroup)
        rv.adapter = adapter

        val layoutManager = LinearLayoutManager(rv.context)
        rv.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        adapter.setTaskList(viewModel.getGroupTasks())
    }


}