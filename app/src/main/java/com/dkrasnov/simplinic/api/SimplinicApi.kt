package com.dkrasnov.simplinic.api

import com.dkrasnov.simplinic.data.BigData
import com.dkrasnov.simplinic.data.RedData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Dmitriy on 27.04.2018.
 */
class SimplinicApi @Inject constructor(private val retrofitMock: RetrofitMock) {

    fun requestRedData(): Observable<List<RedData>> {
        return retrofitMock.getRedDataObservable("get red data query").map { it.map { RedData(it) } }
    }

    fun requestBigData(): Observable<List<BigData>> {
        return retrofitMock.getBigDataObservable("get big data query").map { it.map { BigData(it) } }
    }
}