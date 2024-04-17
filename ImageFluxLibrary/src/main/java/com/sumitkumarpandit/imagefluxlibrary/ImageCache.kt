package com.sumitkumarpandit.imagefluxlibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageCache {
    private const val CACHE_DIRECTORY = "image_cache"

    // saving the image in internal storage
    fun storeBitmap(bitmap: Bitmap, context: Context, fileName: String) {
        val directory = File(context.filesDir, "CACHE_DIRECTORY")
        if (!directory.exists()) {
            directory.mkdirs() // Create the directory if it doesn't exist
        }
        val fileOutputStream = FileOutputStream(File(directory, fileName))
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    // loading the cached image from internal storage
    fun loadBitmap(context: Context, fileName: String): Bitmap? {
        val file = File(context.filesDir, "CACHE_DIRECTORY/$fileName")
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }
}