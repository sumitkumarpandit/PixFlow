package com.sumitkumarpandit.imagefluxlibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageCache {
    private const val CACHE_DIRECTORY = "image_cache"

    fun saveBitmap(context: Context, url: String, bitmap: Bitmap) {
        val cacheDir = File(context.cacheDir, CACHE_DIRECTORY)
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        val file = File(cacheDir, url.hashCode().toString())
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getBitmap(context: Context, url: String): Bitmap? {
        val cacheDir = File(context.cacheDir, CACHE_DIRECTORY)
        val file = File(cacheDir, url.hashCode().toString())
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }
}