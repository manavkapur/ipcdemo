package com.supremebuilds.ipcdemo


import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val providerUri = Uri.parse("content://com.supremebuilds.ipcdemo.myprovider/data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INSERT data (IPC call)
        val values = ContentValues().apply {
            put("text", "Hello from Activity Process!")
        }
        contentResolver.insert(providerUri, values)

        // QUERY data (IPC call)
        val cursor = contentResolver.query(providerUri, null, null, null, null)

        cursor?.moveToFirst()
        val result = cursor?.getString(cursor.getColumnIndexOrThrow("text"))
        Log.d("IPC-ACTIVITY", "Read from provider: $result")

        cursor?.close()
    }
}
