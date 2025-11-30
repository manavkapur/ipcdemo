package com.supremebuilds.ipcdemo


import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {

            val data = Parcel.obtain()
            val reply = Parcel.obtain()

            // Send IPC request to remote binder
            binder?.transact(1, data, reply, 0)

            val msg = reply.readString()
            Log.d("IPC-ACTIVITY", "Message from remote binder: $msg")

            data.recycle()
            reply.recycle()
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
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
