package uz.gita.eventappbroadcast

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.gita.eventappbroadcast.databinding.ActivityMainBinding
import uz.gita.eventappbroadcast.db.SharedPref

class MainActivity : AppCompatActivity() {

    lateinit var receiver: MyBroadCastReceiver
    private lateinit var binding: ActivityMainBinding

    private val sharedPref by lazy { SharedPref.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
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