package com.alfa_sc1.mastur

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyDataSejarah(
    var id: String? = null,
    var nama: String? = null,
    var description: String? = null,
    var url: String? = null,
    var location: String? = null
) : Parcelable
