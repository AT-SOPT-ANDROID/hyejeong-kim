package org.sopt.at.core.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AutoLogin(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

    fun saveLoginInfo(id: String, pw: String) {
        pref.edit {
            putString("userId", id)
            putString("userPw", pw)
            putBoolean("isLoggedIn", true)
            apply()
        }
    }

    fun getLoginInfo(key: String): String = pref.getString(key, "") ?: ""

    fun isLoggedIn(): Boolean = pref.getBoolean("isLoggedIn", false)

    fun logout() {
        pref.edit {
            clear()
            apply()
        }
    }
}