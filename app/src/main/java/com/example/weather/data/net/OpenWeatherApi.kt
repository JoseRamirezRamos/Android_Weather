package com.example.weather.data.net

import com.example.weather.data.model.WeatherClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://api.openweathermap.org/data/2.5/onecall?lat=51.51&lon=-0.13&appid=d0fd70a2b149f6c18eb0fef30d3a741f&units=metric

interface OpenWeatherApi {

    @GET("data/2.5/onecall")
    fun getWeather(
        @Query("lat")lat: String,
        @Query("lon")lon: String,
        @Query("appid")appid: String,
        @Query("units")units: String,
        @Query("lang")lang: String
    ): Call<WeatherClass>

}