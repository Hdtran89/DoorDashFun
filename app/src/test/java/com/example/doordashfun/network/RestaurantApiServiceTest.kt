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
        enqueueResponse("restaurant.json")
        val restaurant = service.getRestaurants("lat", "lng").blockingFirst()

        Assert.assertEquals(2, restaurant.size)
        Assert.assertEquals("662193",restaurant[0].id)
        Assert.assertEquals("65207",restaurant[1].id)
    }

    @Test
    fun fetchDetailRestaurant() {
        enqueueResponse("detail-restaurant.json")
        val restaurant = service.getDetailRestaurant("id").blockingFirst()

        Assert.assertEquals("662193", restaurant.id)
        Assert.assertEquals("McDonald's (16328-MT VIEW)", restaurant.name)
        Assert.assertEquals("Fast Food, Burgers, Ice Cream, Breakfast, American (New), Dinner", restaurant.description)
        Assert.assertEquals(0, restaurant.delivery_fee)
    }

}
