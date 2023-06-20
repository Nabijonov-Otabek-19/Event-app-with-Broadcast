package uz.gita.eventappbroadcast.utils

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uz.gita.eventappbroadcast.R
import uz.gita.eventappbroadcast.data.ActionEnum
import uz.gita.eventappbroadcast.db.SharedPref

data class Event(
    val intent: String
)

data class SwitchData(
    val id: ActionEnum,
    @DrawableRes
    val iconRes: Int,
    @StringRes
    val nameRes: Int,
    val isEnabled : Boolean
)


val allEvents = listOf(
    Event(intent = Intent.ACTION_SCREEN_ON),
    Event(intent = Intent.ACTION_SCREEN_OFF),
    Event(intent = Intent.ACTION_POWER_CONNECTED),
    Event(intent = Intent.ACTION_POWER_DISCONNECTED),
    Event(intent = Intent.ACTION_AIRPLANE_MODE_CHANGED),
    Event(intent = Intent.ACTION_BATTERY_OKAY),
    Event(intent = Intent.ACTION_BATTERY_LOW),
    Event(intent = Intent.ACTION_SHUTDOWN),
    Event(intent = Intent.ACTION_TIMEZONE_CHANGED),
    Event(intent = Intent.ACTION_TIME_CHANGED),
    Event(intent = Intent.ACTION_DATE_CHANGED),
    Event(intent = BluetoothAdapter.ACTION_STATE_CHANGED)
)

val allSwitchData = listOf(
    SwitchData(
        id = ActionEnum.SCREEN,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.screen_on,
        isEnabled = SharedPref.getInstance().screenAction
    ),
    SwitchData(
        id = ActionEnum.BLUETOOTH,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.bluetooth_state_changed,
        isEnabled = SharedPref.getInstance().bluetoothAction
    ),
    SwitchData(
        id = ActionEnum.BATTERY_LOW,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.battery_low,
        isEnabled = SharedPref.getInstance().batteryLowAction
    ),
    SwitchData(
        id = ActionEnum.PILOT,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.airplane_mode_changed,
        isEnabled = SharedPref.getInstance().pilotAction
    ),
    SwitchData(
        id = ActionEnum.POWER,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.power_connected,
        isEnabled = SharedPref.getInstance().powerAction
    ),
    SwitchData(
        id = ActionEnum.SHUTDOWN,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.action_shutdown,
        isEnabled = SharedPref.getInstance().shutdownAction
    ),
    SwitchData(
        id = ActionEnum.BATTERY_OK,
        iconRes = R.drawable.ic_logo,
        nameRes = R.string.battery_ok,
        isEnabled = SharedPref.getInstance().batteryOkAction
    )
)