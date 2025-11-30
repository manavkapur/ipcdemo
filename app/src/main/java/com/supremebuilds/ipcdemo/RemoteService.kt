package com.supremebuilds.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemoteService : Service() {

    private val binder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d("IPC-SERVICE", "RemoteService created in remote process")
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("IPC-SERVICE", "Client bound — returning Binder")
        return binder
    }
}