package com.example.doordashfun.network

import com.example.doordashfun.data.remote.api.RestaurantApiService
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RestaurantApiServiceTest : ApiAbstract<RestaurantApiService>() {
    private lateinit var service: RestaurantApiService

    @Before
    fun initService() {
        this.service = createService(RestaurantApiService::class.java)
    }

    @Test
    fun fetchRestaurants() {
        enqueueResponse()
        val restaurant = service.getRestaurants("lat", "lng").blockingFirst()

        Assert.assertEquals(1, restaurant.size)
        Assert.assertEquals("id",restaurant[0].id)
    }

    @Test
    fun fetchDetailRestaurant() {
        enqueueResponse()
        val restaurant = service.getDetailRestaurant("id").blockingFirst()

        Assert.assertEquals("id", restaurant.id)
        Assert.assertEquals("name", restaurant.name)
        Assert.assertEquals("description", restaurant.description)
        Assert.assertEquals("delivery_fee", restaurant.delivery_fee)
    }

}
