package com.example.doordashfun.di.module

import com.example.doordashfun.ui.restaurant.detail.RestaurantDetailActivity
import com.example.doordashfun.ui.restaurant.main.RestaurantActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): RestaurantActivity

    @ContributesAndroidInjector
    abstract fun contributeRestaurantDetailActivity(): RestaurantDetailActivity

}
