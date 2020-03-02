package com.test.appwhere.di.module

import com.test.appwhere.app.ui.controllers.controllers.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity() : LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeAlmacenActivity() : MainActivity


}