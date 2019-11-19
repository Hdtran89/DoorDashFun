package com.example.doordashfun

import android.app.Application
import com.example.doordashfun.di.DaggerAppComponent
import com.example.doordashfun.di.module.ApiModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppController : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .build()
            .inject(this)
    }
}
