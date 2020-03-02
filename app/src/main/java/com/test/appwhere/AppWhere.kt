package com.test.appwhere

import com.icom.baselivedata.config.BaseConfig
import com.test.appwhere.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppWhere : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        BaseConfig.BASE_URL = "http://166.62.33.53:4590/"
    }
}