package com.android.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    val name: String,
    val url: String
) : Parcelable