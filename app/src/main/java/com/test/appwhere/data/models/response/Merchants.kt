package com.test.appwhere.data.models.response

import com.google.gson.annotations.SerializedName

data class Merchants(
    @SerializedName("id") val id : String?,
    @SerializedName("merchantName") val merchantName: String,
    @SerializedName("merchantAddress") val merchantAddress : String?,
    @SerializedName("merchantTelephone") val merchantTelephone: String,
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("registrationDate") val registrationDate: String
    )