package com.supremebuilds.ipcdemo


import android.content.*
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var serviceMessenger: Messenger? = null

    // Handler to receive reply from service
    private val replyHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 2) {
                val reply = msg.data?.getString("reply")
                Log.d("IPC", "Reply from service: $reply")
            }
        }

    }

    private val replyMessenger = Messenger(replyHandler)

    // Service connection
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            serviceMessenger = Messenger(binder)

            val msg = Message.obtain(null, 1)

            val bundle = Bundle()
            bundle.putString("data", "Hello from Activity!")
            msg.data = bundle

            msg.replyTo = replyMessenger

            serviceMessenger?.send(msg)
        }


        override fun onServiceDisconnected(name: ComponentName?) {
            serviceMessenger = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, RemoteService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}
