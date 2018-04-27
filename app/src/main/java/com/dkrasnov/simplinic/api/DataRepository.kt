package com.dkrasnov.simplinic.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dkrasnov.simplinic.data.BigData
import com.dkrasnov.simplinic.data.RedData
import com.dkrasnov.simplinic.data.livedata.LiveResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Dmitriy on 27.04.2018.
 */
class DataRepository @Inject constructor(val preferences: Preferences, val simplinicApi: SimplinicApi) {

    val redLiveData: MutableLiveData<LiveResource<List<RedData>>> = MutableLiveData()
    val bigLiveData: MutableLiveData<LiveResource<List<BigData>>> = MutableLiveData()

    fun requestRedDataLiveData(): LiveData<LiveResource<List<RedData>>> {
        val cachedData = preferences.getRedData()
        if (cachedData != null) {
            redLiveData.value = LiveResource.success(parseCacheData(cachedData).map { RedData(it) })

            return redLiveData
        }

        redLiveData.value = LiveResource.loading(null)

        simplinicApi.requestRedData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            redLiveData.value = LiveResource.success(it)
            preferences.putRedData(convertToCacheData(it.map { it.value }))
        }, {
            redLiveData.value = LiveResource.error(it, null)
        })

        return redLiveData
    }

    fun requestBigDataLiveData(): LiveData<LiveResource<List<BigData>>> {
        val cachedData = preferences.getBigData()
        if (cachedData != null) {
            bigLiveData.value = LiveResource.success(parseCacheData(cachedData).map { BigData(it) })

            return bigLiveData
        }

        bigLiveData.value = LiveResource.loading(null)

        simplinicApi.requestBigData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            bigLiveData.value = LiveResource.success(it)
            preferences.putBigData(convertToCacheData(it.map { it.value }))
        }, {
            bigLiveData.value = LiveResource.error(it, null)
        })

        return bigLiveData
    }

    fun clearCache() {
        preferences.putRedData(null)
        preferences.putBigData(null)
    }

    private fun parseCacheData(data: String): List<String> {
        return data.split(";")
    }

    private fun convertToCacheData(data: List<String>): String {
        var result = ""

        data.forEachIndexed { index, value ->
            if (index != 0) result += ";"

            result += value
        }

        return result
    }
}