package com.test.appwhere.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icom.baselivedata.utils.Resource
import com.test.appwhere.data.models.request.RegisterMerchantRequest
import com.test.appwhere.data.repository.AppWhereRepository
import com.test.appwhere.domain.models.DomainResponse
import com.test.appwhere.domain.models.GetLoginDomain
import com.test.appwhere.domain.models.GetMerchantsDomain
import com.test.appwhere.domain.models.RegisterMerchantDomain
import com.test.appwhere.domain.usecase.AppWhereUseCase
import com.test.appwhere.domain.usecase.impl.AppWhereUseCaseImpl
import javax.inject.Inject

class AppWhereViewModel
@Inject
constructor(repository : AppWhereRepository) : ViewModel() {

    private var loginUsecase : AppWhereUseCase? = null
    private val getMerchantsUseCase : AppWhereUseCase
    private var requestMerchantsUsecase : AppWhereUseCase? = null
    //View data
    val loginLiveData : LiveData<Resource<DomainResponse<GetLoginDomain, String>, String>>
    val getMerchantsLiveData : LiveData<Resource<DomainResponse<GetMerchantsDomain, String>, String>>
    val requestMerchantsLiveData : LiveData<Resource<DomainResponse<RegisterMerchantDomain, String>, String>>
    //View Errors
    var errorlogin : LiveData<String>

    init {
        loginUsecase = AppWhereUseCaseImpl(repository)
        getMerchantsUseCase = AppWhereUseCaseImpl(repository)
        requestMerchantsUsecase = AppWhereUseCaseImpl(repository)
        errorlogin = (loginUsecase as AppWhereUseCaseImpl).errorlogin

        loginLiveData = (loginUsecase as AppWhereUseCaseImpl).loginLiveData
        getMerchantsLiveData =getMerchantsUseCase.getMerchantsLiveData
        requestMerchantsLiveData = (requestMerchantsUsecase as AppWhereUseCaseImpl).requestMerchantsLiveData
    }

    fun login(user :String, pass : String){
        loginUsecase?.login(user,pass)
    }

    fun getMerchants(){
        getMerchantsUseCase?.getmerchants()
    }

    fun requestMerchants(registerMerchantRequest: RegisterMerchantRequest){
        requestMerchantsUsecase?.registermerchants(registerMerchantRequest)
    }

}