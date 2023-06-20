package uz.gita.eventappbroadcast.broadcast

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import uz.gita.eventappbroadcast.R
import uz.gita.eventappbroadcast.db.SharedPref

class MyBroadCastReceiver : BroadcastReceiver() {

    private lateinit var audio: MediaPlayer
    private val sharedPref by lazy { SharedPref.getInstance() }

    override fun onReceive(context: Context?, intent: Intent?) {

        val pilotMode = intent?.getBooleanExtra("state", false)

        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                if (sharedPref.screenAction) {
                    audio = MediaPlayer.create(context, R.raw.screen_on)
                    audio.start()
                }
            }

            Intent.ACTION_SCREEN_OFF -> {
                if (sharedPref.screenAction) {
                    audio = MediaPlayer.create(context, R.raw.screen_off)
                    audio.start()
                }
            }

            Intent.ACTION_POWER_CONNECTED -> {
                if (sharedPref.powerAction) {
                    audio = MediaPlayer.create(context, R.raw.power_connected)
                    audio.start()
                }
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                if (sharedPref.powerAction) {
                    audio = MediaPlayer.create(context, R.raw.power_disconnected)
                    audio.start()
                }
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (sharedPref.pilotAction) {
                    if (pilotMode!!) {
                        audio = MediaPlayer.create(context, R.raw.airplane_on)
                        audio.start()
                    } else {
                        audio = MediaPlayer.create(context, R.raw.airplane_off)
                        audio.start()
                    }
                }
            }

            Intent.ACTION_BATTERY_LOW -> {
                audio = MediaPlayer.create(context, R.raw.battery_low)
                audio.start()
            }

            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                when (state) {
                    BluetoothAdapter.STATE_OFF -> {
                        if (sharedPref.bluetoothAction) {
                            audio = MediaPlayer.create(context, R.raw.bluetooth_off)
                            audio.start()
                        }
                    }

                    BluetoothAdapter.STATE_ON -> {
                        if (sharedPref.bluetoothAction) {
                            audio = MediaPlayer.create(context, R.raw.bluetooth_on)
                            audio.start()
                        }
                    }
                }
            }
        }
    }
}