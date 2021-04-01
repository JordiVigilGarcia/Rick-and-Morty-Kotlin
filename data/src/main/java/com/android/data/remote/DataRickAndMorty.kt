package com.android.data.remote

import android.icu.text.IDNA
import android.os.Parcelable
import com.android.data.models.RickMorty
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class DataRickAndMorty(
    val info: @RawValue Info,
    val results: ArrayList<RickMorty>
) : Parcelable