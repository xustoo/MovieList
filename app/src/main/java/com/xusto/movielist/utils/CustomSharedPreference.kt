package com.xusto.movielist.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreference {


    companion object {
        private val PREFERENCE_TIME = "preference_time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreference? = null

        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreference =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreference(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreference(context: Context): CustomSharedPreference {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreference()

        }
    }
    fun saveTime(time : Long){
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCE_TIME, time)
        }
    }
    fun getTime() = sharedPreferences?.getLong(PREFERENCE_TIME,0)
}