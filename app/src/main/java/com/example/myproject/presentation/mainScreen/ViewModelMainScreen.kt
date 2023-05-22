package com.example.myproject.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.example.myproject.data.local.model.Task
import com.example.myproject.data.local.model.TaskDataModel
import com.example.myproject.domain.UseCase

class ViewModelMainScreen : ViewModel() {

    private val tasksUseCase = UseCase

    fun getMainScreenTasks(): ArrayList<Task> {
        return tasksUseCase.getMainScreenTasksToUseCase()
    }

    fun getTasks(): ArrayList<Task> {
        return tasksUseCase.getTasksToUseCase()
    }

    fun deleteTask(position: Int) {
        tasksUseCase.useCaseDeleteTask(position)
    }

}