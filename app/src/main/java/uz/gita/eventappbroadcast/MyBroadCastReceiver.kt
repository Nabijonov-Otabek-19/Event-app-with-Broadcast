package uz.gita.eventappbroadcast

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log

class MyBroadCastReceiver : BroadcastReceiver() {

    private lateinit var audio: MediaPlayer

    override fun onReceive(context: Context?, intent: Intent?) {

        val pilotMode = intent?.getBooleanExtra("state", false)

        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                Log.d("AAA", "Screen on")
                audio = MediaPlayer.create(context, R.raw.screen_on)
                audio.start()
            }

            Intent.ACTION_SCREEN_OFF -> {
                Log.d("AAA", "Screen off")
                audio = MediaPlayer.create(context, R.raw.screen_off)
                audio.start()
            }

            Intent.ACTION_POWER_CONNECTED -> {
                Log.d("AAA", "Power connected")
                audio = MediaPlayer.create(context, R.raw.power_connected)
                audio.start()
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                Log.d("AAA", "Power disconnected")
                audio = MediaPlayer.create(context, R.raw.power_disconnected)
                audio.start()
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (pilotMode!!) {
                    Log.d("AAA", "Pilot mode on")
                    audio = MediaPlayer.create(context, R.raw.airplane_on)
                    audio.start()
                } else {
                    Log.d("AAA", "Pilot mode off")
                    audio = MediaPlayer.create(context, R.raw.airplane_off)
                    audio.start()
                }
            }

            Intent.ACTION_BATTERY_LOW -> {
                Log.d("AAA", "Battery low")
                audio = MediaPlayer.create(context, R.raw.battery_low)
                audio.start()
            }

            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                Log.d("AAA", "Bluetooth")
                val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                when (state) {
                    BluetoothAdapter.STATE_OFF -> {
                        Log.d("AAA", "Bluetooth off")
                        audio = MediaPlayer.create(context, R.raw.bluetooth_off)
                        audio.start()
                    }

                    BluetoothAdapter.STATE_ON -> {
                        Log.d("AAA", "Bluetooth on")
                        audio = MediaPlayer.create(context, R.raw.bluetooth_on)
                        audio.start()
                    }
                }
            }
        }
    }
}