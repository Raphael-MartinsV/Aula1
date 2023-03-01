package com.example.aula1

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_PREF_INT, Context.MODE_PRIVATE)

    var intExamplePref: Int
        get() = preferences.getInt(APP_PREF_INT, -1)
        set(value) = preferences.edit().putInt(APP_PREF_INT, value).apply()


    companion object {
        private const val APP_PREF_INT = "intExamplePref"
    }
}