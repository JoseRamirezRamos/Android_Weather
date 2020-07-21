package com.example.weather.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
): Parcelable
