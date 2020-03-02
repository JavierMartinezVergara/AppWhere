package com.test.appwhere.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.icom.baselivedata.utils.Resource
import com.icom.baselivedata.utils.Status
import com.test.appwhere.data.models.request.RegisterMerchantRequest
import com.test.appwhere.data.repository.AppWhereRepository
import com.test.appwhere.domain.models.DomainResponse
import com.test.appwhere.domain.models.GetLoginDomain
import com.test.appwhere.domain.models.GetMerchantsDomain
import com.test.appwhere.domain.models.RegisterMerchantDomain
import com.test.appwhere.domain.usecase.AppWhereUseCase
import javax.inject.Inject

class AppWhereUseCaseImpl
@Inject
constructor(val repository: AppWhereRepository) : AppWhereUseCase {


    //Catch events
    private var getloginRequestLiveData : MutableLiveData<Array<String>> = MutableLiveData()
    private var getMerchantsRequestLiveData : MutableLiveData<Boolean> = MutableLiveData()
    private var postRequestMerchantsRequestLiveData : MutableLiveData<RegisterMerchantRequest> = MutableLiveData()


    //Repositorio
    private var repoRequestLoginLiveData : LiveData<Resource<DomainResponse<GetLoginDomain, String>, String>>
    private var repoRequestMerchantsLiveData : LiveData<Resource<DomainResponse<RegisterMerchantDomain, String>, String>>

    //View data
    var loginLiveData : LiveData<Resource<DomainResponse<GetLoginDomain,String>, String>>
    var getMerchantsLiveData : LiveData<Resource<DomainResponse<GetMerchantsDomain, String>, String>>
    var requestMerchantsLiveData : LiveData<Resource<DomainResponse<RegisterMerchantDomain, String>, String>>

    //View Errors
    var errorlogin : MutableLiveData<String> = MutableLiveData()


    init {
        repoRequestLoginLiveData = Transformations.switchMap(getloginRequestLiveData) {
            repository.login(it[0],it[1])
        }
        loginLiveData = Transformations.map(repoRequestLoginLiveData) {
            when(it.status){
                Status.ERROR -> Resource.error<DomainResponse<GetLoginDomain,String>, String>(it.message)
                Status.LOADING -> Resource.loading<DomainResponse<GetLoginDomain,String>, String>(null)
                Status.SUCCESS -> {
                    if(it.data?.data?.successful==true) {
                        Resource.success<DomainResponse<GetLoginDomain,String>, String>(it.data)
                    } else {
                        Resource.error<DomainResponse<GetLoginDomain,String>, String>(it?.data?.error)
                    }
                }
            }
        }

        getMerchantsLiveData = Transformations.switchMap(getMerchantsRequestLiveData){
            repository.getMerchants()
        }

        repoRequestMerchantsLiveData = Transformations.switchMap(postRequestMerchantsRequestLiveData) {
            repository.registerMerchants(it)
        }
        requestMerchantsLiveData = Transformations.map(repoRequestMerchantsLiveData) {
            when(it.status){
                Status.ERROR -> Resource.error<DomainResponse<RegisterMerchantDomain,String>, String>(it.message)
                Status.LOADING -> Resource.loading<DomainResponse<RegisterMerchantDomain,String>, String>(null)
                Status.SUCCESS -> {
                    if(it.data?.data?.status==1) {
                        Resource.success<DomainResponse<RegisterMerchantDomain,String>, String>(it.data)
                    } else {
                        Resource.error<DomainResponse<RegisterMerchantDomain,String>, String>(it?.data?.error)
                    }
                }
            }
        }
    }


    override fun login(user: String, pass: String) {

            getloginRequestLiveData.value= arrayOf(user,pass)
    }
    override fun getmerchants() {
        getMerchantsRequestLiveData.value =true
    }

    override fun registermerchants(registerMerchantRequest: RegisterMerchantRequest) {
        postRequestMerchantsRequestLiveData.value = registerMerchantRequest
    }




}