package com.example.myproject.data.local.model

class TaskDataBase {

    private var taskProperties: TaskDataModel? = null
    var list = ArrayList<Task>()
    var listForMainScreen = ArrayList<Task>()
    private var listOfEndedTasks = ArrayList<Task>()


    fun createTask(
        name: String,
        description: String,
        group: String,
        dateFormat: String,
        timeFormat: String,
    ) {
        list.add(0, Task(name, description, group, dateFormat, timeFormat))
        listForMainScreen.add(0, Task(name, description, group, dateFormat, timeFormat))
        taskProperties = TaskDataModel(list)
    }

    fun deleteTaskForMainScreen(boolean: Boolean, position: Int) {
        if (boolean) {
            list.remove(list[position])
            listOfEndedTasks.remove(listOfEndedTasks[position])
        } else {
            listOfEndedTasks.add(listForMainScreen[position])
            listForMainScreen.remove(listForMainScreen[position])
        }
    }
    fun getEndedTasks(): ArrayList<Task> {
        return listOfEndedTasks
    }
}