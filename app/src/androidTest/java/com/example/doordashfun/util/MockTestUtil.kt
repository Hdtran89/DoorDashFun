package com.example.doordashfun.util

import com.example.doordashfun.data.remote.model.Restaurant

class MockTestUtil {
    companion object {
        fun mockDetailRestaurantApiResponse(): Restaurant {
            val restaurant = Restaurant("1", "name1", "description1", "image_url1", "status1", 0)
            return restaurant
        }

        fun mockRestaurantsApiResponse(): List<Restaurant> {
            val restaurantList = arrayListOf<Restaurant>()
            restaurantList.add(Restaurant("1", "name1", "description1", "image_url1", "status1", 0))
            restaurantList.add(Restaurant("2", "name2", "description2", "image_url2", "status2", 0))
            restaurantList.add(Restaurant("3", "name3", "description3", "image_url3", "status3", 0))
            return restaurantList
        }
    }
}
