package com.example.weather.data.model

import android.os.Parcel
import android.os.Parcelable

data class Daily(
    val dt: Int,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp?,
    val feelsLike: FeelsLike?,
    val pressure: Long,
    val humidity: Long,
    val dewPoint: Double,
    val windSpeed: Double,
    val windDeg: Long,
    val weather: List<Weather>,
    val clouds: Long,
    val rain: Double? = null,
    val uvi: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        TODO("temp"),
        TODO("feelsLike"),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readLong(),
        TODO("weather"),
        parcel.readLong(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dt)
        parcel.writeLong(sunrise)
        parcel.writeLong(sunset)
        parcel.writeLong(pressure)
        parcel.writeLong(humidity)
        parcel.writeDouble(dewPoint)
        parcel.writeDouble(windSpeed)
        parcel.writeLong(windDeg)
        parcel.writeLong(clouds)
        parcel.writeValue(rain)
        parcel.writeDouble(uvi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Daily> {
        override fun createFromParcel(parcel: Parcel): Daily {
            return Daily(parcel)
        }

        override fun newArray(size: Int): Array<Daily?> {
            return arrayOfNulls(size)
        }
    }
}