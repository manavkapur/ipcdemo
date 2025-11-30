package com.supremebuilds.ipcdemo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class RemoteService : Service() {

    // Handler that receives messages from Activity
    private val incomingHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    val clientMsg = msg.data?.getString("data")
                    Log.d("IPC-SERVICE", "Received: $clientMsg")

                    val reply = Message.obtain(null, 2)
                    val bundle = Bundle()
                    bundle.putString("reply", "Hello from Remote Service!")
                    reply.data = bundle

                    msg.replyTo.send(reply)
                }
            }
        }

    }

    // Messenger connected to this service
    private val messenger = Messenger(incomingHandler)

    override fun onBind(intent: Intent?): IBinder {
        return messenger.binder
    }
}
