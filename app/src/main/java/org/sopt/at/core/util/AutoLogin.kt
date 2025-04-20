package org.sopt.at.core.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AutoLogin(context: Context) {

    companion object {
        private const val PREF_NAME = "userInfo"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_PW = "userPw"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveLoginInfo(id: String, pw: String) {
        pref.edit {
            putString(KEY_USER_ID, id)
            putString(KEY_USER_PW, pw)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun getLoginInfo(key: String): String = pref.getString(key, "") ?: ""

    fun isLoggedIn(): Boolean = pref.getBoolean(KEY_IS_LOGGED_IN, false)

    fun logout() {
        pref.edit {
            clear()
            apply()
        }
    }
}