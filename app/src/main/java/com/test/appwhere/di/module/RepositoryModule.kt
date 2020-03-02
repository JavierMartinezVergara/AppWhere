package com.test.appwhere.di.module

import com.test.appwhere.data.api.ApiService
import com.test.appwhere.data.repository.AppWhereRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAccessRepository(accessApi: ApiService) = AppWhereRepository(accessApi)


}