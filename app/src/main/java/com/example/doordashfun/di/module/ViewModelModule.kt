package com.example.doordashfun.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.doordashfun.factory.ViewModelFactory
import com.example.doordashfun.ui.restaurant.detail.RestaurantDetailViewModel
import com.example.doordashfun.ui.restaurant.main.RestaurantViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    protected abstract fun restaurantViewModel(restaurantViewModel: RestaurantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailViewModel::class)
    protected abstract fun restaurantDetailViewModel(restaurantDetailViewModel: RestaurantDetailViewModel): ViewModel
}
