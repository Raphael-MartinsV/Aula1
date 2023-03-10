package com.example.aula1.ui.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.aula1.R
import com.example.aula1.ui.main.MainActivity
import com.example.aula1.ui.notification.NotificationActionReceiver

object NotificationUtils {

    private const val CHANNEL_ID = "default"

    private fun getSoundUri(context: Context): Uri =
        Uri.parse("android.resource://${context.packageName}/raw/notification_sound")

    private fun getContentIntent(context: Context): PendingIntent? {
        val mainIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(MainActivity.EXTRA_MESSAGE, "Via notificação")
        }
        return TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(mainIntent)
            .getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = context.getString(R.string.notify_channel_name)
        val channelDescription = context.getString(R.string.notify_channel_description)
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.GREEN
            enableVibration(true)
            setSound(
                getSoundUri(context), AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
            )
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun notificationWithTapAction(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_drive_eta_24)
            .setContentTitle(context.getString(R.string.notify_title))
            .setContentText(context.getString(R.string.notify_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.BLUE)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(getContentIntent(context))
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notificationBuilder.build())
    }

    fun notificationWithButtonAction(context: Context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
        val actionIntent = Intent(context, NotificationActionReceiver::class.java).apply {
            putExtra(NotificationActionReceiver.EXTRA_MESSAGE, "Ação da notificação")
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 2, actionIntent, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_drive_eta_24)
            .setContentTitle(context.getString(R.string.notify_title))
            .setContentText(context.getString(R.string.notify_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.BLUE)
            .setDefaults(Notification.DEFAULT_ALL)
            .addAction(0, "Ação", pendingIntent)
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2, notificationBuilder.build())
    }
}