package com.example.myproject.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.example.myproject.data.local.model.Task
import com.example.myproject.domain.UseCase

class ViewModelMainScreen : ViewModel() {

    private val tasksUseCase = UseCase

    fun getMainScreenTasks(): ArrayList<Task> {
        return tasksUseCase.getMainScreenTasksToUseCase()
    }

    fun deleteTask(position: Int) {
        tasksUseCase.useCaseDeleteMainScreenTask(false, position)
    }
}