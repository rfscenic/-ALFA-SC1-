package com.alfa_sc1.mastur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataPenginapan(
    var name_penginapan: String,
    var description_penginapan: String,
    var photo_penginapan: String,
    var location_penginapan: String,
    var harga_penginapan:String
) : Parcelable