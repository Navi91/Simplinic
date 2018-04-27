package com.dkrasnov.simplinic.api

import io.reactivex.Observable

/**
 * Created by Dmitriy on 27.04.2018.
 */
class RetrofitMock {

    fun getRedDataObservable(query: String): Observable<List<String>> {
        return Observable.create {
            Thread.sleep(3000)

            it.onNext(listOf("one", "two", "three"))
        }
    }

    fun getBigDataObservable(query: String): Observable<List<String>> {
        return Observable.create {
            Thread.sleep(5000)

            it.onNext(listOf("one big", "two big", "three big"))
        }
    }
}