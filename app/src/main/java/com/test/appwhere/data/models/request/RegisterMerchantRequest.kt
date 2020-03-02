package com.test.appwhere.data.models.request

import com.google.gson.annotations.SerializedName

data class RegisterMerchantRequest (

    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double,
    @SerializedName("merchantAddress") val merchantAddress: String,
    @SerializedName("merchantName") val merchantName : String,
    @SerializedName("merchantTelephone") val merchantTelephone : String
)