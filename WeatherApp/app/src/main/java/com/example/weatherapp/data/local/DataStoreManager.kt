package com.example.weatherapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.data.local.DataStoreManager.Companion.DATA_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

class DataStoreManager(context: Context) {
    companion object {
        const val DATA_STORE_NAME = "data_manager"
    }

    private object PreferencesKeys {
        val KEY_USER_NAME = stringPreferencesKey("KEY_USER_NAME")
        val KEY_USER_AGE = intPreferencesKey("KEY_USER_AGE")
    }

    private val dataStore = context.dataStore

    suspend fun setUserName(name: String) {
        dataStore.edit {
            it[PreferencesKeys.KEY_USER_NAME] = name
        }
    }

    val getUserName: Flow<String?> = dataStore.data.map { pref ->
        pref[PreferencesKeys.KEY_USER_NAME]
    }
}