package com.example.doordashfun.ui.restaurant.detail

import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.repository.RestaurantRepository
import com.example.doordashfun.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantDetailViewModel@Inject constructor(restaurantApiService: RestaurantApiService)  : BaseViewModel() {
    private val restaurantRepo: RestaurantRepository = RestaurantRepository(restaurantApiService)

    fun fetchDetailsRestaurant(){

    }
}
