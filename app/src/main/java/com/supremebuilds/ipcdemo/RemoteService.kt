package com.supremebuilds.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {

    private val binder = object : IMyAidlInterface.Stub() {
        override fun getMessage(): String {
            return "Hello from Remote Process!"
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
