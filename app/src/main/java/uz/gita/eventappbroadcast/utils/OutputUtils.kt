package uz.gita.eventappbroadcast.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun logger(msg: String) {
    Log.d("AAA", msg)
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}