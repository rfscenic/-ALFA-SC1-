package com.alfa_sc1.mastur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataKuliner(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable