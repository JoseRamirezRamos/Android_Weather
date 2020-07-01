package com.example.weather.data.model

data class WeatherClass (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Long,
    val current: Current,
    val hourly: List<Hourly>,
    val daily: List<Daily>
)