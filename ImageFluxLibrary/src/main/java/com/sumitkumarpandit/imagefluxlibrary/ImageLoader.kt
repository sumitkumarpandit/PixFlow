package com.sumitkumarpandit.imagefluxlibrary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ImageLoader {
    private val cache = HashMap<String, Bitmap>()
    private val executor: ExecutorService = Executors.newFixedThreadPool(5)
    private val handler = Handler(Looper.getMainLooper())

    fun loadImage(url: String, callback: (Bitmap?) -> Unit) {
        if (cache.containsKey(url)) {
            callback(cache[url])
        } else {
            executor.execute {
                try {
                    val bitmap = downloadBitmap(url)
                    cache[url] = bitmap!!
                    handler.post { callback(bitmap) }
                } catch (e: Exception) {
                    handler.post { callback(null) }
                }
            }
        }
    }

    private fun downloadBitmap(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        var connection: HttpURLConnection? = null
        try {
            val imageUrl = URL(url)
            connection = imageUrl.openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.connect()
            val inputStream = connection.inputStream
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        return bitmap
    }
}
