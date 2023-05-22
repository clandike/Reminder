package com.example.myproject.presentation.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.data.local.model.Task
import com.example.myproject.databinding.TaskCardBinding
import com.example.myproject.notification.notificationID

class AdapterMainScreen(val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<AdapterMainScreen.TaskViewHolder>() {

    private val tList = ArrayList<Task>()

    inner class TaskViewHolder(val binding: TaskCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindItem(task: Task, position: Int) {
            binding.chTvTitle.text = task.name
            binding.chTvMessage.text = task.description
            binding.chTvGroup.text = task.group
            binding.tvDate.text = task.dateFormat
            binding.tvTime.text = notificationID.toString()
            binding.deleteTaskButton.setOnClickListener {
                onClick(position)
                tList.remove(tList[position])
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val task = TaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(task)
    }

    override fun getItemCount(): Int {
        return tList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindItem(tList[position], position)
    }

    fun setTaskList(newList: ArrayList<Task>) {
        tList.addAll(newList)
        notifyDataSetChanged()
    }


}