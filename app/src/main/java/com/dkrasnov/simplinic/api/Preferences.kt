package com.dkrasnov.simplinic.api

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Dmitriy on 27.04.2018.
 */
class Preferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val RED_DATA_PREF_KEY = "red_data_pref_key"
    private val BIG_DATA_PREF_KEY = "big_data_pref_key"

    fun putRedData(data: String?) {
        sharedPreferences.edit().putString(RED_DATA_PREF_KEY, data).apply()
    }

    fun getRedData(): String? {
        return sharedPreferences.getString(RED_DATA_PREF_KEY, null)
    }

    fun putBigData(data: String?) {
        sharedPreferences.edit().putString(BIG_DATA_PREF_KEY, data).apply()
    }

    fun getBigData(): String? {
        return sharedPreferences.getString(BIG_DATA_PREF_KEY, null)
    }
}