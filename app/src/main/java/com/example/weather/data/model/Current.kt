package com.example.weather.data.model

data class Current (
    val dt: Int,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val feels_like: Double,
    val pressure: Double,
    val humidity: Double,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Double,
    val visibility: Double,
    val windSpeed: Double,
    val windDeg: Double,
    val weather: List<Weather>
)
