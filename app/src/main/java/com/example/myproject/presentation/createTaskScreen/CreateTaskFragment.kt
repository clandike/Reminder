package com.example.myproject.presentation.createTaskScreen

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myproject.R
import com.example.myproject.notification.channelID
import com.example.myproject.notification.messageExtra
import com.example.myproject.notification.notificationID
import com.example.myproject.notification.titleExtra
import java.text.DateFormat
import java.util.*

class CreateTaskFragment : Fragment() {

    private lateinit var dateFormat: DateFormat
    private lateinit var timeFormat: DateFormat

    private val viewModel = ViewModelCreateTask()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.create_task_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = view.findViewById<Button>(R.id.confirm_button)
        btn.setOnClickListener {
            notificationID = notificationID+1
            val name = view.findViewById<EditText>(R.id.etTitle)
            val description = view.findViewById<EditText>(R.id.etMessage)
            val group = view.findViewById<EditText>(R.id.etGroup)

            createNotificationChannel()
            findNavController().navigate(R.id.createTask_to_main).apply {
                scheduleNotification()
                timeFormat = android.text.format.DateFormat.getTimeFormat(context?.applicationContext)
                dateFormat = android.text.format.DateFormat.getLongDateFormat(context?.applicationContext)

                viewModel.addTask(
                    name.text.toString(),
                    description.text.toString(),
                    group.text.toString(),
                    dateFormat.format(Date(getTime())),
                    timeFormat.format(Date(getTime()))
                )
            }
        }
    }

    private fun scheduleNotification() {
        val intent = Intent(
            context?.applicationContext,
            com.example.myproject.notification.Notification::class.java
        )
        val title = view?.findViewById<EditText>(R.id.etTitle)?.text.toString()
        val message = view?.findViewById<EditText>(R.id.etMessage)?.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)


        val pendingIntent = PendingIntent.getBroadcast(
            context?.applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    private fun getTime(): Long {
        val tp = view?.findViewById<TimePicker>(R.id.timePicker)
        val dp = view?.findViewById<DatePicker>(R.id.datePicker)

        val minute = tp?.minute
        val hour = tp?.hour
        val day = dp?.dayOfMonth
        val month = dp?.month
        val year = dp?.year

        val calendar = Calendar.getInstance()
        calendar.set(year!!, month!!, day!!, hour!!, minute!!)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            context?.applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}