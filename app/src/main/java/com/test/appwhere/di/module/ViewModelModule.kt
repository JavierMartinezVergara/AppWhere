package com.test.appwhere.di.module

import androidx.lifecycle.ViewModel
import com.test.appwhere.app.viewmodels.*
import com.test.appwhere.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppWhereViewModel::class)
    abstract fun bindLoginViewModel(viewModel: AppWhereViewModel) : ViewModel


}