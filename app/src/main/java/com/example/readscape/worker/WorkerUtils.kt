package com.example.readscape.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.readscape.CHANNEL_ID
import com.example.readscape.NOTIFICATION_ID
import com.example.readscape.NOTIFICATION_TEXT
import com.example.readscape.NOTIFICATION_TITLE
import com.example.readscape.R
import com.example.readscape.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.readscape.VERBOSE_NOTIFICATION_CHANNEL_NAME

fun buildNotification(context: Context): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_stat_name)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(NOTIFICATION_TEXT)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
}

fun showNotification(context: Context, notification: NotificationCompat.Builder) {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification.build())
}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
        val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
