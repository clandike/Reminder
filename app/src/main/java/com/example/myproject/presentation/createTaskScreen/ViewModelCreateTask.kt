package com.example.myproject.presentation.createTaskScreen

import androidx.lifecycle.ViewModel
import com.example.myproject.domain.UseCase

class ViewModelCreateTask : ViewModel() {

    private val tasksUseCase = UseCase

    fun addTask(
        name: String,
        description: String,
        group: String,
        dateFormat: String,
        timeFormat: String
    ) {
        tasksUseCase.addTaskToUseCase(name, description, group, dateFormat,timeFormat)
    }
}