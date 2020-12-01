package com.alfa_sc1.mastur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataSejarah(
    var name: String,
    var description: String,
    var photo: String,
    var location: String
) : Parcelable
