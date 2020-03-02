package com.test.appwhere.di.module

import com.test.appwhere.app.ui.controllers.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFragment() : AddFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentPagoVisa() : MapsFragment

    @ContributesAndroidInjector
    abstract fun contributeFragmentHomeInvite() : RecyclerFragment

    




}