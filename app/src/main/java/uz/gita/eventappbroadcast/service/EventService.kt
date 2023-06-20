package uz.gita.eventappbroadcast.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import uz.gita.eventappbroadcast.R
import uz.gita.eventappbroadcast.utils.allEvents
import uz.gita.eventappbroadcast.broadcast.MyBroadCastReceiver

class EventService : Service() {

    companion object {
        val CHANNEL_ID = "EVENT_ID"
        val CHANNEL_NAME = "EVENT"
        val STOP_SERVICE = "stop"
    }

    private val receiver = MyBroadCastReceiver()

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        createChannel()
        startService()
        registerAllEvents()
    }

    private fun registerAllEvents() {
        registerReceiver(receiver, IntentFilter().apply {
            for (event in allEvents) {
                addAction(event.intent)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(receiver)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            channel.setSound(null, null)
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(channel)
        }
    }

    private fun startService() {
        val stopIntent = Intent(this, EventService::class.java)
        stopIntent.putExtra(STOP_SERVICE, true)
        val stopPendingIntent = PendingIntent
            .getService(
                this,
                1,
                stopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .addAction(R.drawable.app_logo, STOP_SERVICE, stopPendingIntent)
            .setOngoing(true)
            .build()

        startForeground(1, notification)
    }
}