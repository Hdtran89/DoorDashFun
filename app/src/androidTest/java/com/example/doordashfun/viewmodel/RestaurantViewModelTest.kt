package com.example.doordashfun.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.ui.restaurant.main.RestaurantViewModel
import com.example.doordashfun.util.MockTestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RestaurantViewModelTest {
    @Mock
    private lateinit var observer: Observer<List<Restaurant>>

    @Mock
    private lateinit var restaurantApiService: RestaurantApiService

    private lateinit var restaurantViewModel: RestaurantViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.restaurantViewModel = RestaurantViewModel(restaurantApiService)
    }

    @Test
    fun loadRestaurantDetails() {
        val restaurant = MockTestUtil.mockRestaurantsApiResponse()

        restaurantViewModel.getRestaurantLiveData().observeForever(observer)
        restaurantViewModel.fetchRestaurants()

        assert(restaurantViewModel.getRestaurantLiveData().value == restaurant)
    }
}
