package com.example.readscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.readscape.data.BookDatabase
import com.example.readscape.model.UserDao
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.readscape.ui.LoginScreen
import com.example.readscape.ui.theme.ReadscapeTheme
import com.example.readscape.worker.ReadScapeWorker
import com.example.readscape.worker.createNotificationChannel
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDao = BookDatabase.getInstance(this).userDao()
        createNotificationChannel(this)

        setContent {
            ReadscapeTheme() {
                ReadScapeApp(userDao)
            }
        }

        scheduleReadScapeWorker()
    }
    private fun scheduleReadScapeWorker() {
        val workRequest = OneTimeWorkRequestBuilder<ReadScapeWorker>()
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}
