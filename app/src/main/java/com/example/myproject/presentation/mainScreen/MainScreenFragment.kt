package com.example.myproject.presentation.mainScreen

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.notification.notificationID

class MainScreenFragment : Fragment() {

    lateinit var viewModel: ViewModelMainScreen
    lateinit var adapter: AdapterMainScreen
    private var positionTask = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelMainScreen::class.java)
        adapter = AdapterMainScreen { position ->
            viewModel.deleteTask(position)
            cancelNotification(context?.applicationContext!!, 3)
        }
        adapter.setTaskList(viewModel.getMainScreenTasks())

        val btn = view.findViewById<Button>(R.id.createTaskBtn)
        btn.setOnClickListener {
            findNavController().navigate(R.id.main_to_createTask)
        }

        val rv = view.findViewById<RecyclerView>(R.id.tasksRv)
        rv.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        rv.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        adapter.setTaskList(viewModel.getMainScreenTasks())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun cancelNotification(ctx: Context, position: Int) {
        val notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(position)
    }

}