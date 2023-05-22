package com.example.myproject.presentation.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.notification.notificationID

class MainScreenFragment : Fragment() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ViewModelMainScreen::class.java)

        val adapter = AdapterMainScreen { position ->
            viewModel.deleteTask(position)
            //context?.let { cancelNotification(it.applicationContext, notificationID) }
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


//        toggle = ActionBarDrawerToggle(
//            this@MainActivity,
//            binding.drawerLayout,
//            R.string.open,
//            R.string.close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        navView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.allTasks -> {}
//                R.id.todayTasks -> {}
//                R.id.laterTasks -> {}
//            }
//            true
//        }
    }

    // private fun cancelNotification(ctx: Context, notifyId: Int) {
    //     val notificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    //     notificationManager.cancel(notifyId)
    //      notificationManager.cancel(notifyId+1)
    // }
}