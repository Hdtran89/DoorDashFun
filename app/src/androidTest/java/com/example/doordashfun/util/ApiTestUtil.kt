package com.example.doordashfun.util

import io.reactivex.Observable

class ApiTestUtil<T> {

    fun createCall(data: T): Observable<T> {
        return Observable.just(data)
    }
}
