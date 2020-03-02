package com.test.appwhere.di.module

import com.test.appwhere.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesApiService(client: Retrofit) : ApiService {
        return client.create(ApiService::class.java)
    }
}