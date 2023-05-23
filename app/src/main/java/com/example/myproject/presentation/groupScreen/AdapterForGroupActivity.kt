package com.example.myproject.presentation.groupScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.data.local.model.Task
import com.example.myproject.databinding.TaskCardBinding
import com.example.myproject.domain.UseCase.groupName

class AdapterForGroupActivity(val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<AdapterForGroupActivity.TaskViewHolder>() {

    private val tList = ArrayList<Task>()

    inner class TaskViewHolder(val binding: TaskCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(task: Task, position: Int) {
            if (groupName == "CompletedTasks") {
                binding.deleteTaskButton.text = "REMOVE"
            }
            binding.chTvTitle.text = task.name
            binding.chTvMessage.text = task.description
            binding.chTvGroup.text = task.group
            binding.tvDate.text = task.dateFormat
            binding.tvTime.text = task.timeFormat
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
        tList.clear()
        tList.addAll(newList)
        notifyDataSetChanged()
    }
}
