package org.sopt.at.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref_auth")

class AuthPreferences(private val context: Context) {

    companion object {
        val USER_ID_KEY = longPreferencesKey("userId")
    }

    suspend fun setUserId(userId: Long) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = userId
        }
    }

    suspend fun logout() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
        }
    }

    val userId: Flow<Long> = context.dataStore.data
        .map { it[USER_ID_KEY] ?: 0L }
}