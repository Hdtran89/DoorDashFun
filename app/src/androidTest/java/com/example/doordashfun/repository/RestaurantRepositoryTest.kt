package com.example.doordashfun.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.doordashfun.data.remote.api.RestaurantApiService
import com.example.doordashfun.data.remote.model.Restaurant
import com.example.doordashfun.data.remote.repository.RestaurantRepository
import com.example.doordashfun.util.MockTestUtil
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class RestaurantRepositoryTest {

    @Mock
    lateinit var restaurantApiService: RestaurantApiService

    lateinit var restaurantRepository: RestaurantRepository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.restaurantRepository = RestaurantRepository(this.restaurantApiService)
    }

    @Test
    fun loadRestaurants() {

        `when`(restaurantApiService.getRestaurants(Mockito.anyString(), Mockito.anyString())).thenReturn(
            Observable.just(
                MockTestUtil.mockRestaurantsApiResponse()
            )
        )

        val data = restaurantRepository.loadRestaurants()
        verify(restaurantApiService).getRestaurants(Mockito.anyString(), Mockito.anyString())

        val observable = TestObserver<List<Restaurant>>()
        data.subscribe(observable)
        observable.assertNoErrors()
    }

    @Test
    fun loadDetailRestaurant() {
        `when`(restaurantApiService.getDetailRestaurant(Mockito.anyString())).thenReturn(
            Observable.just(
                MockTestUtil.mockDetailRestaurantApiResponse()
            )
        )

        val data = restaurantRepository.loadDetailRestaurant(Mockito.anyString())
        verify(restaurantApiService).getDetailRestaurant(Mockito.anyString())

        val observable = TestObserver<Restaurant>()
        data.subscribe(observable)
        observable.assertNoErrors()
    }
}
