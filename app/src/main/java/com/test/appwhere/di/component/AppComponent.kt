package com.test.appwhere.di.component

import android.app.Application
import com.icom.baselivedata.di.module.BaseRetrofitModule
import com.test.appwhere.di.annotation.PerActivity
import com.test.appwhere.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@PerActivity
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    BaseRetrofitModule::class,
    ActivityModule::class,
    FragmentModule::class,
    RepositoryModule::class,
    ApiModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}