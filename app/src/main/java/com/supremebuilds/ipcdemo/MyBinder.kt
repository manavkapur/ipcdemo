package com.supremebuilds.ipcdemo

import android.os.Binder
import android.os.Parcel

class MyBinder : Binder() {

    companion object {
        const val GET_MESSAGE = 1
    }

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {

        return when (code) {
            GET_MESSAGE -> {
                reply?.writeString("Hello from Manual Binder IPC!")
                true
            }
            else -> super.onTransact(code, data, reply, flags)
        }
    }
}
