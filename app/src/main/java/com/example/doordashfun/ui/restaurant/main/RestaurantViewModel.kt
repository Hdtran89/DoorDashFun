package com.example.doordashfun.ui.restaurant.main

import androidx.lifecycle.MutableLiveData
import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.data.remote.repository.RestaurantRepository
import com.example.doordashfun.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(restaurantApiService: RestaurantApiService) :
    BaseViewModel() {
    private var restaurantLiveData: MutableLiveData<List<Restaurant>> = MutableLiveData()

    private val restaurantRepo: RestaurantRepository = RestaurantRepository(restaurantApiService)

    fun fetchRestaurants() {
        restaurantRepo.loadRestaurants()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { addToDisposable(it) }
            .subscribe { getRestaurantLiveData().postValue(it) }

    }

    fun getRestaurantLiveData() = restaurantLiveData
}
