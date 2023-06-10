package com.example.readscape.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class ReadScapeWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams)  {

    override suspend fun doWork(): Result {
        val notification = buildNotification(applicationContext)
        showNotification(applicationContext, notification)

        rescheduleWorker()

        return Result.success()
    }

    private fun rescheduleWorker() {
        val workRequest = OneTimeWorkRequestBuilder<ReadScapeWorker>()
            .setInitialDelay(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}