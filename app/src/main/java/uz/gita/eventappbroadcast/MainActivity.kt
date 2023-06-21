package uz.gita.eventappbroadcast

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.eventappbroadcast.adapter.EventAdapter
import uz.gita.eventappbroadcast.data.ActionEnum
import uz.gita.eventappbroadcast.databinding.ActivityMainBinding
import uz.gita.eventappbroadcast.db.SharedPref
import uz.gita.eventappbroadcast.service.EventService
import uz.gita.eventappbroadcast.utils.Data
import uz.gita.eventappbroadcast.utils.checkPermissions
import uz.gita.eventappbroadcast.utils.logger

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedPref by lazy { SharedPref.getInstance() }
    private val adapter by lazy { EventAdapter(Data.allSwitchData()) }

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

        binding.apply {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)

            adapter.setClickListener { id, isEnabled ->
                startService()
                when (id) {
                    ActionEnum.SCREEN -> sharedPref.screenAction = isEnabled
                    ActionEnum.PILOT -> sharedPref.pilotAction = isEnabled
                    ActionEnum.POWER -> sharedPref.powerAction = isEnabled
                    ActionEnum.BLUETOOTH -> sharedPref.bluetoothAction = isEnabled
                    ActionEnum.SHUTDOWN -> sharedPref.shutdownAction = isEnabled
                    ActionEnum.BATTERY_OK -> sharedPref.batteryOkAction = isEnabled
                    ActionEnum.BATTERY_LOW -> sharedPref.batteryLowAction = isEnabled
                    ActionEnum.TIME -> sharedPref.timeAction = isEnabled
                    ActionEnum.TIME_ZONE -> sharedPref.timeZoneAction = isEnabled
                }
            }
        }
    }

    private fun startService(){
        val intent = Intent(this, EventService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(intent)
        } else {
            this.startService(intent)
        }
    }
}