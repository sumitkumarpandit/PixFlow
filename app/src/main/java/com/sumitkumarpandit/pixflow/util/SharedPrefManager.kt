package com.sumitkumarpandit.pixflow.util

import android.content.Context
import androidx.core.content.edit

object SharedPrefManager {
    private const val PREF_NAME = "app_settings"

    fun saveString(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(key, value)
            apply()
        }
    }

    fun getString(context: Context, key: String, defaultValue: String): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}