package com.example.doordashfun.di

import android.app.Application
import com.example.doordashfun.AppController
import com.example.doordashfun.di.module.ActivityModule
import com.example.doordashfun.di.module.ApiModule
import com.example.doordashfun.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModuleInfo: ApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)
}
