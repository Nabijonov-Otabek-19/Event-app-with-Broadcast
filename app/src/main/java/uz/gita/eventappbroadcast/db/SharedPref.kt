package uz.gita.eventappbroadcast.db

import android.content.Context
import android.content.SharedPreferences

class SharedPref {

    companion object {
        private lateinit var instance: SharedPref

        private const val SHARED_PREF = "shared_pref"

        private const val SCREEN_ACTION = "screen_action"
        private const val NETWORK_ACTION = "network_action"
        private const val POWER_ACTION = "power_action"
        private const val PILOT_ACTION = "pilot_action"
        private const val BLUETOOTH_ACTION = "bluetooth_action"
        private const val SHUTDOWN_ACTION = "shutdown_action"
        private const val BATTERY_LOW_ACTION = "battery_low_action"
        private const val BATTERY_OK_ACTION = "battery_ok_action"
        private const val TIME_ACTION = "time_action"
        private const val TIME_ZONE_ACTION = "time_zone_action"

        private lateinit var pref: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor

        fun init(context: Context) {
            pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
            editor = pref.edit()

            if (!(::instance.isInitialized)) {
                instance = SharedPref()
            }
        }

        fun getInstance() = instance
    }

    var screenAction: Boolean
        get() = pref.getBoolean(SCREEN_ACTION, false)
        set(value) = editor.putBoolean(SCREEN_ACTION, value).apply()

    var networkAction: Boolean
        get() = pref.getBoolean(NETWORK_ACTION, false)
        set(value) = editor.putBoolean(NETWORK_ACTION, value).apply()

    var powerAction: Boolean
        get() = pref.getBoolean(POWER_ACTION, false)
        set(value) = editor.putBoolean(POWER_ACTION, value).apply()

    var pilotAction: Boolean
        get() = pref.getBoolean(PILOT_ACTION, false)
        set(value) = editor.putBoolean(PILOT_ACTION, value).apply()

    var bluetoothAction: Boolean
        get() = pref.getBoolean(BLUETOOTH_ACTION, false)
        set(value) = editor.putBoolean(BLUETOOTH_ACTION, value).apply()

    var shutdownAction: Boolean
        get() = pref.getBoolean(SHUTDOWN_ACTION, false)
        set(value) = editor.putBoolean(SHUTDOWN_ACTION, value).apply()

    var batteryLowAction: Boolean
        get() = pref.getBoolean(BATTERY_LOW_ACTION, false)
        set(value) = editor.putBoolean(BATTERY_LOW_ACTION, value).apply()

    var batteryOkAction: Boolean
        get() = pref.getBoolean(BATTERY_OK_ACTION, false)
        set(value) = editor.putBoolean(BATTERY_OK_ACTION, value).apply()

    var timeAction: Boolean
        get() = pref.getBoolean(TIME_ACTION, false)
        set(value) = editor.putBoolean(TIME_ACTION, value).apply()

    var timeZoneAction: Boolean
        get() = pref.getBoolean(TIME_ZONE_ACTION, false)
        set(value) = editor.putBoolean(TIME_ZONE_ACTION, value).apply()
}