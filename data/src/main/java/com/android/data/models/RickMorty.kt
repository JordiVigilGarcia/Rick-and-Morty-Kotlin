package com.android.data.models

import android.os.Parcelable
import com.android.data.remote.LocationName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class RickMorty(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: @RawValue LocationName,
    val image: String,
    val episode: ArrayList<String>

    //Others options for characters

    //val origin: @RawValue Data
    //val type: String

) : Parcelable {}