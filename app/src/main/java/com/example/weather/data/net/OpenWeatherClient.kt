package com.example.weather.data.net

import com.example.weather.data.model.WeatherClass
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://api.openweathermap.org/data/2.5/onecall?lat=51.51&lon=-0.13&appid=d0fd70a2b149f6c18eb0fef30d3a741f&units=metric

object OpenWeatherClient {

    private val openWeatherApi: OpenWeatherApi

    private const val API_KEY = "d0fd70a2b149f6c18eb0fef30d3a741f"
    private const val UNITS = "metric"
    private const val OPEN_WEATHER_URL = "http://api.openweathermap.org/"
    private val coordinates = Pair("32.63", "-115.45")
    private const val LANG = "es"

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        openWeatherApi = retrofit.create(OpenWeatherApi::class.java)
    }

    fun getWeather(lang: String = LANG, lat: String = coordinates.first, lon: String = coordinates.second): Call<WeatherClass> {
        return openWeatherApi.getWeather(lat,lon, API_KEY, UNITS, lang)
    }

}