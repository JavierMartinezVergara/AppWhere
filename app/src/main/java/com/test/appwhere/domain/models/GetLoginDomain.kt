package com.test.appwhere.domain.models


data class GetLoginDomain (

    val status : Int,
    val description : String,
    val userId : String,
    val successful : Boolean)
