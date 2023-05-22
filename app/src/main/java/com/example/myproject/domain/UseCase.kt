package com.example.myproject.domain

import com.example.myproject.data.local.model.Task
import com.example.myproject.data.local.model.TaskDataBase

object UseCase {

    private val tasksData = TaskDataBase()

    fun addTaskToUseCase(
        name: String,
        descriptor: String,
        group: String,
        dateFormat: String,
        timeFormat: String,
    ) {
        tasksData.createTask(name, descriptor, group, dateFormat, timeFormat)
    }

    fun getTasksToUseCase(): ArrayList<Task> {
        return tasksData.list
    }

    fun getMainScreenTasksToUseCase(): ArrayList<Task> {
        return tasksData.listForMainScreen
    }


    fun useCaseDeleteTask(position: Int) {
        tasksData.deleteTask(position)
    }
}