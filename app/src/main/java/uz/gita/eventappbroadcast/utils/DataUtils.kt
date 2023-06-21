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
    val isChecked: Boolean
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

object Data {
    val allSwitchData = arrayListOf<SwitchData>()

    init {
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.BLUETOOTH,
                iconRes = R.drawable.bluetooth,
                nameRes = R.string.bluetooth_state_changed,
                isChecked = SharedPref.getInstance().bluetoothAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.BATTERY_LOW,
                iconRes = R.drawable.battery_low,
                nameRes = R.string.battery_low,
                isChecked = SharedPref.getInstance().batteryLowAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.PILOT,
                iconRes = R.drawable.pilot,
                nameRes = R.string.airplane_mode_changed,
                isChecked = SharedPref.getInstance().pilotAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.SCREEN,
                iconRes = R.drawable.screen,
                nameRes = R.string.screen_on,
                isChecked = SharedPref.getInstance().screenAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.POWER,
                iconRes = R.drawable.power,
                nameRes = R.string.power_connected,
                isChecked = SharedPref.getInstance().powerAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.SHUTDOWN,
                iconRes = R.drawable.shutdown,
                nameRes = R.string.action_shutdown,
                isChecked = SharedPref.getInstance().shutdownAction
            )
        )
        allSwitchData.add(
            SwitchData(
                id = ActionEnum.BATTERY_OK,
                iconRes = R.drawable.battery_ok,
                nameRes = R.string.battery_ok,
                isChecked = SharedPref.getInstance().batteryOkAction
            )
        )
    }
}