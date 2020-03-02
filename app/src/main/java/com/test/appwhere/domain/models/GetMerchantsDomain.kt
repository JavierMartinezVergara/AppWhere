
package com.test.appwhere.domain.models



data class GetMerchantsDomain (

	val status : Int?,
	val description : String,
	val merchants : List<MerchantsDomain>
)