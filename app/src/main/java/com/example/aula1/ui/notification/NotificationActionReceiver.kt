package com.example.aula1.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {
    //Receiver que é configura a ação de click do botão
    //Também é delacrado no manifest
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context,
            intent.getStringExtra(EXTRA_MESSAGE),
            Toast.LENGTH_LONG
        ).show()
        NotificationManagerCompat.from(context).cancelAll()
    }

    companion object {
        const val EXTRA_MESSAGE = "message"
    }

}