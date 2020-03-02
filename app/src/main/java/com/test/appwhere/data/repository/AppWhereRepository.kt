package com.test.appwhere.data.repository

import androidx.lifecycle.LiveData
import com.icom.baselivedata.repository.ProcessedNetworkResource
import com.icom.baselivedata.repository.api.ApiResponse
import com.icom.baselivedata.utils.Resource
import com.test.appwhere.data.api.ApiService
import com.test.appwhere.data.models.request.RegisterMerchantRequest
import com.test.appwhere.data.models.response.GetLogin
import com.test.appwhere.data.models.response.GetMerchants
import com.test.appwhere.data.models.response.RegisterMerchantResponse
import com.test.appwhere.domain.models.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppWhereRepository
@Inject
constructor(private val apiService: ApiService) {

     fun login(user : String , pass : String) : LiveData<Resource<DomainResponse<GetLoginDomain, String>, String>>{

        return object : ProcessedNetworkResource<GetLogin,DomainResponse<GetLoginDomain,String>,String>(){
            override fun createCall(): LiveData<ApiResponse<GetLogin>>{
                return apiService.getLogiResult(user,pass)
            }

            override fun mapError(body: String) = "Ha ocurrido un error"

            override fun processResponse(response: GetLogin): DomainResponse<GetLoginDomain,String> {
                if (response.successful) {
                    return DomainResponse(GetLoginDomain(response.status,response.description, response.userId, response.successful) , null)
                } else {
                    return DomainResponse(null, response.description)
                }
            }
        }.asLiveData()
    }

    fun getMerchants() : LiveData<Resource<DomainResponse<GetMerchantsDomain, String>, String>>{

        return object : ProcessedNetworkResource<GetMerchants,DomainResponse<GetMerchantsDomain,String>,String>(){
            override fun createCall(): LiveData<ApiResponse<GetMerchants>>{
                return apiService.getMerchants()
            }

            override fun mapError(body: String) = "Ha ocurrido un error"

            override fun processResponse(response: GetMerchants): DomainResponse<GetMerchantsDomain,String> {
                if (response.status == 1) {
                    return DomainResponse(GetMerchantsDomain(response.status,response.description, response.merchants.map{
                        MerchantsDomain(it.id, it.merchantName, it.merchantAddress, it.merchantTelephone, it.latitude, it.longitude, it.registrationDate)
                    }) , null)
                } else {
                    return DomainResponse(null, response.description)
                }
            }
        }.asLiveData()
    }

    fun registerMerchants(registerMerchantRequest: RegisterMerchantRequest) : LiveData<Resource<DomainResponse<RegisterMerchantDomain, String>, String>>{

        return object : ProcessedNetworkResource<RegisterMerchantResponse,DomainResponse<RegisterMerchantDomain,String>,String>(){
            override fun createCall(): LiveData<ApiResponse<RegisterMerchantResponse>>{
                return apiService.registerMerchants(registerMerchantRequest)
            }

            override fun mapError(body: String) = "Ha ocurrido un error"

            override fun processResponse(response: RegisterMerchantResponse): DomainResponse<RegisterMerchantDomain,String> {
                if (response.status == 1) {
                    return DomainResponse(RegisterMerchantDomain(response.status,response.description, response.merchants?.map{
                        MerchantsDomain(it.id, it.merchantName, it.merchantAddress, it.merchantTelephone, it.latitude, it.longitude, it.registrationDate)
                    }) , null)
                } else {
                    return DomainResponse(null, response.description)
                }
            }
        }.asLiveData()
    }

}