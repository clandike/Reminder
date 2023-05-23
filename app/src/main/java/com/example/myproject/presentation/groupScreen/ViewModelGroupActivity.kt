package com.example.myproject.presentation.groupScreen

import androidx.lifecycle.ViewModel
import com.example.myproject.data.local.model.Task
import com.example.myproject.domain.UseCase
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewModelGroupActivity : ViewModel() {

    private val tasksUseCase = UseCase
    var nameGroup = tasksUseCase.groupName
    private val tList = tasksUseCase.getMainScreenTasksToUseCase()

    fun getGroupTasks(): ArrayList<Task> {
        return when (nameGroup) {
            "CompletedTasks" ->{
                return tasksUseCase.getEndedTasksToUseCase()
            }

            "Work" -> {
                val taskList = ArrayList<Task>()
                for (i in 0 until tList.size) {
                    if (tList[i].group.toString() == nameGroup) {
                        taskList.add(tList[i])
                    }
                }
                return taskList
            }
            "Study" -> {
                val taskList = ArrayList<Task>()
                for (i in 0 until tList.size) {
                    if (tList[i].group.toString() == nameGroup) {
                        taskList.add(tList[i])
                    }
                }
                return taskList
            }
            "TODAY_TASKS@" -> {
                val taskList = ArrayList<Task>()
                for (i in 0 until tList.size) {
                    if (tList[i].dateFormat == DateFormat.getDateInstance().format(Date())) {
                        taskList.add(tList[i])
                    }
                }
                return taskList
            }
            "LATER_TASKS@" -> {
                val taskList = ArrayList<Task>()
                for (i in 0 until tList.size) {
                    if (tList[i].dateFormat!! > DateFormat.getDateInstance().format(Date())) {
                        taskList.add(tList[i])
                    }
                }
                return taskList
            }
            else -> {
                arrayListOf(Task(" ", " ", " ", " ", " "))
            }
        }
    }

    fun deleteMainSreenTask(boolean: Boolean,position: Int) {
        tasksUseCase.useCaseDeleteMainScreenTask(boolean,position)
    }


}