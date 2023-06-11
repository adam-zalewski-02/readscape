package com.example.readscape.ui

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun setLoggedInStatus(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean("is_logged_in", isLoggedIn)
            apply()
        }
    }

    fun getLoggedInStatus(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    fun setLoggedUserId(userId: Int) {
        with(sharedPreferences.edit()) {
            putInt("logged_user_id", userId)
            apply()
        }
    }

    fun getLoggedUserId(): Int {
        return sharedPreferences.getInt("logged_user_id", 0)
    }
}
