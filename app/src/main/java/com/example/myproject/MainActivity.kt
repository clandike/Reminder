package com.example.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.domain.UseCase
import com.example.myproject.presentation.groupScreen.GroupActivity
import com.example.myproject.presentation.groupScreen.ViewModelGroupActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, GroupActivity::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                binding.drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.completedTasks -> {
                        UseCase.putNameGroup("CompletedTasks")
                        startActivity(intent)
                    }
                    R.id.todayTasks -> {
                        UseCase.putNameGroup("TODAY_TASKS@")
                        startActivity(intent)
                    }
                    R.id.laterTasks -> {
                        UseCase.putNameGroup("LATER_TASKS@")
                        startActivity(intent)
                    }
                    R.id.workTasks -> {
                        UseCase.putNameGroup("Work")
                        startActivity(intent)
                    }
                    R.id.studyTasks -> {
                        UseCase.putNameGroup("Study")
                        startActivity(intent)
                    }

                }
                true
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}