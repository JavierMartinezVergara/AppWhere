package com.test.appwhere.app.ui.controllers.models

import android.os.Parcel
import android.os.Parcelable

data class MerchantsMarkers(
    val merchantName: String,
    val latitude : Double,
    val longitude: Double): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.let {
            parcel.writeString(merchantName)
            parcel.writeDouble(latitude)
            parcel.writeDouble(longitude)

        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MerchantsMarkers> {
        override fun createFromParcel(parcel: Parcel): MerchantsMarkers{
            return MerchantsMarkers(parcel)
        }

        override fun newArray(size: Int): Array<MerchantsMarkers?> {
            return arrayOfNulls(size)
        }
    }

}