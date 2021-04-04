package com.android.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LocationName(
    val name: String
) : Parcelable