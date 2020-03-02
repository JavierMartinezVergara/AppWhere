package com.test.appwhere.data.api

import androidx.lifecycle.LiveData
import com.icom.baselivedata.repository.api.ApiResponse
import com.test.appwhere.data.models.request.RegisterMerchantRequest
import com.test.appwhere.data.models.response.GetLogin
import com.test.appwhere.data.models.response.GetMerchants
import com.test.appwhere.data.models.response.RegisterMerchantResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("api/session/login")
    fun getLogiResult(@Query("email") email: String,
                      @Query("password") password : String) : LiveData<ApiResponse<GetLogin>>

    @GET("get-merchants")
    fun getMerchants(): LiveData<ApiResponse<GetMerchants>>

    @POST("register-merchant")
    fun registerMerchants(@Body registerMerchantRequest: RegisterMerchantRequest): LiveData<ApiResponse<RegisterMerchantResponse>>


}