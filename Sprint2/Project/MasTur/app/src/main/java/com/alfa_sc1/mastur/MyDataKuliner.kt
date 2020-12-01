package com.alfa_sc1.mastur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataKuliner(
    var name_kuliner: String,
    var description_kuliner: String,
    var photo_kuliner: String,
    var harga_kuliner: String,
    var location_kuliner:String
) : Parcelable