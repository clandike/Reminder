package com.example.myproject.data.local.model


final data class TaskDataModel(
    val tasks: ArrayList<Task> = arrayListOf(),
)

data class Task(
    val name: String?,
    val description: String?,
    val group: String?,
    val dateFormat: String,
    val timeFormat: String,
)