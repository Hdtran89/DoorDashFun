package com.example.doordashfun.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.ui.restaurant.detail.RestaurantDetailViewModel
import com.example.doordashfun.util.MockTestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class RestaurantDetailViewModelTest {
    @Mock
    private lateinit var observer: Observer<Restaurant>

    @Mock
    private lateinit var restaurantApiService: RestaurantApiService

    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.restaurantDetailViewModel = RestaurantDetailViewModel(restaurantApiService)
    }

    @Test
    fun loadRestaurantDetails() {
        val restaurant = MockTestUtil.mockDetailRestaurantApiResponse()

        restaurantDetailViewModel.getRestaurantDetailLiveData().observeForever(observer)
        restaurantDetailViewModel.fetchDetailsRestaurant(restaurant.id)

        assert(restaurantDetailViewModel.getRestaurantDetailLiveData().value == restaurant)
    }
}
