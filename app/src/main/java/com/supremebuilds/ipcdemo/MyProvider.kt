package com.supremebuilds.ipcdemo


import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

class MyProvider : ContentProvider() {

    private var storedText = "Default value"

    override fun onCreate(): Boolean {
        Log.d("IPC-PROVIDER", "Provider created in remote process")
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        storedText = values?.getAsString("text") ?: "No Value"
        Log.d("IPC-PROVIDER", "Inserted: $storedText")
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        Log.d("IPC-PROVIDER", "Query called")

        // Build a single-row in-memory cursor
        val matrix = android.database.MatrixCursor(arrayOf("text"))
        matrix.addRow(arrayOf(storedText))
        return matrix
    }

    override fun getType(uri: Uri): String? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?) = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?) = 0
}
