package com.test.appwhere.domain.models

data class MerchantsDomain(
    val id : String?,
    val merchantName: String,
    val merchantAddress : String?,
    val merchantTelephone: String,
    val latitude : Double,
    val longitude: Double,
    val registrationDate: String
    )