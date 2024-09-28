package com.sharkdroid.houseme.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.sharkdroid.houseme.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User_Setting")

class LocalUserManagerImp(
    private val context: Context
) : LocalUserManager {

    override suspend fun saveLoginEntry() {
        Log.d("LocalUserManagerImp", "Saving login entry as true")
        context.dataStore.edit { preferences ->
            preferences[PreferenceKey.LOGIN_ENTRY] = true
        }
    }

    override fun readLoginEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val status = preferences[PreferenceKey.LOGIN_ENTRY] ?: false
            Log.d("LocalUserManagerImp", "Reading login entry: $status")
            status
        }
    }

}

private object PreferenceKey {
    val LOGIN_ENTRY = booleanPreferencesKey("LoginEntry")
}
