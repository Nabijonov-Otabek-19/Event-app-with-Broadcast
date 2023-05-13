package uz.gita.eventappbroadcast.app

import android.app.Application
import uz.gita.eventappbroadcast.db.SharedPref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}