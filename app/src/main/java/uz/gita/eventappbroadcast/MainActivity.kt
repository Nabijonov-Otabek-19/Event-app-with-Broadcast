package uz.gita.eventappbroadcast

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.gita.eventappbroadcast.broadcast.MyBroadCastReceiver
import uz.gita.eventappbroadcast.databinding.ActivityMainBinding
import uz.gita.eventappbroadcast.db.SharedPref
import uz.gita.eventappbroadcast.service.EventService
import uz.gita.eventappbroadcast.utils.checkPermissions
import uz.gita.eventappbroadcast.utils.logger

class MainActivity : AppCompatActivity() {

    private lateinit var receiver: MyBroadCastReceiver
    private lateinit var binding: ActivityMainBinding

    private val sharedPref by lazy { SharedPref.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.checkPermissions(
                arrayListOf(
                    android.Manifest.permission.READ_MEDIA_AUDIO,
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                logger("Permission allowed")
            }
        }

        val intent = Intent(this, EventService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(intent)
        } else {
            this.startService(intent)
        }

        binding.apply {

            switchScreen.isChecked = sharedPref.screenAction
            switchNetwork.isChecked = sharedPref.networkAction
            switchPilot.isChecked = sharedPref.pilotAction
            switchPower.isChecked = sharedPref.powerAction
            switchBluetooth.isChecked = sharedPref.bluetoothAction

            switchScreen.setOnCheckedChangeListener { _, isChecked ->
                sharedPref.screenAction = isChecked
            }

            switchNetwork.setOnCheckedChangeListener { _, isChecked ->
                sharedPref.networkAction = isChecked
            }

            switchPilot.setOnCheckedChangeListener { _, isChecked ->
                sharedPref.pilotAction = isChecked
            }

            switchPower.setOnCheckedChangeListener { _, isChecked ->
                sharedPref.powerAction = isChecked
            }

            switchBluetooth.setOnCheckedChangeListener { _, isChecked ->
                sharedPref.bluetoothAction = isChecked
            }
        }

        receiver = MyBroadCastReceiver()

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        }

        this.registerReceiver(receiver, intentFilter)
    }
}