package com.example.doordashfun.data.remote.api

import com.example.doordashfun.data.remote.model.Restaurant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApiService {
    @GET("/v2/restaurant/")
    fun getRestaurants(@Query("lat") lat: String,
                       @Query("lng") lng: String) : Observable<List<Restaurant>>
}