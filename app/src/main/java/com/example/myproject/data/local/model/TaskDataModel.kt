package com.example.myproject.data.local.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")
final data class TaskDataModel(
    @SerializedName("tasks")
    val tasks: ArrayList<Task> = arrayListOf()
)

data class Task(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("group")
    val group: String?,
    @SerializedName("dateFormat")
    val dateFormat: String?,
    @SerializedName("timeFormat")
    val timeFormat: String?,
)