package com.example.readscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.readscape.data.BookDatabase
import com.example.readscape.model.user.UserDao
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.readscape.ui.theme.ReadscapeTheme
import com.example.readscape.worker.ReadScapeWorker
import com.example.readscape.worker.createNotificationChannel

class MainActivity : ComponentActivity() {
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // local database
        userDao = BookDatabase.getInstance(this).userDao()

        // notifications
        createNotificationChannel(this)

        setContent {
            ReadscapeTheme() {
                ReadScapeApp(userDao)
            }
        }


        scheduleReadScapeWorker()
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
    private fun scheduleReadScapeWorker() {
        val workRequest = OneTimeWorkRequestBuilder<ReadScapeWorker>()
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}
