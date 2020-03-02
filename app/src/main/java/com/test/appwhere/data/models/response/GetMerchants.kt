
package com.test.appwhere.data.models.response

import com.google.gson.annotations.SerializedName


data class GetMerchants (

	@SerializedName("status") val status : Int?,
	@SerializedName("description") val description : String,
	@SerializedName("merchants") val merchants : List<Merchants>
)