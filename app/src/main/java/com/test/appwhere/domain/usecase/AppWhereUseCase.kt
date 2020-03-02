package com.test.appwhere.domain.usecase

import com.test.appwhere.data.models.request.RegisterMerchantRequest

interface AppWhereUseCase {
    fun login(user : String, pass:  String)

    fun getmerchants()

    fun registermerchants(registerMerchantRequest: RegisterMerchantRequest)
}