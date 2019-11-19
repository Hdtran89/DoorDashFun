package com.example.doordashfun.data.remote.repository

import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.utils.AppConstants
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class RestaurantRepository(val restaurantApiService: RestaurantApiService) {
    fun loadRestaurants() : Observable<List<Restaurant>> {
        return restaurantApiService.getRestaurants(AppConstants.LATITUDE,AppConstants.LONGITUDE)
    }
}