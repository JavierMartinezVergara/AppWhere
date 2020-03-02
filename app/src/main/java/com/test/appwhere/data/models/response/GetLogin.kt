package com.test.appwhere.data.models.response

import com.google.gson.annotations.SerializedName

data class GetLogin (

    @SerializedName("status") val status : Int,
    @SerializedName("description") val description : String,
    @SerializedName("userId") val userId : String,
    @SerializedName("successful") val successful : Boolean
)