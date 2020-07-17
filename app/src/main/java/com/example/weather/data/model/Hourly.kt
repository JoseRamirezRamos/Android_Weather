package com.example.weather.data.model

data class Hourly (
    val dt: Int,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Long,
    val humidity: Long,
    val dewPoint: Double,
    val clouds: Long,
    val windSpeed: Double,
    val windDeg: Long,
    val weather: List<Weather>,
    val rain: Rain? = null
)
