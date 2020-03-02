package com.test.appwhere.domain.models

import com.google.gson.annotations.SerializedName

data class RegisterMerchantDomain (

    val status : Int,
    val description : String,
    val merchants : List<MerchantsDomain>?
    )