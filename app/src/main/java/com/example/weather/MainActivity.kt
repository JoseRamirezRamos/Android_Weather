package com.example.weather

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.data.model.Current
import com.example.weather.data.model.WeatherClass
import com.example.weather.data.net.OpenWeatherClient
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.indefiniteSnackbar
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    var hourlySummary: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeather()
    }

    private fun getWeather(){
        displayProgressBar(true)
        displayUI(false)
        OpenWeatherClient.getWeather().enqueue(object : retrofit2.Callback<WeatherClass> {
            override fun onFailure(call: Call<WeatherClass>, t: Throwable) {
                displayProgressBar(false)
                displayErrorMessage()
            }
            override fun onResponse(call: Call<WeatherClass>, response: Response<WeatherClass>) {
                displayProgressBar(false)
                displayUI(true)

                if (response.isSuccessful){
                    setUpWidgets(response.body()?.current)
                    hourlySummary = response.body()?.hourly?.map {
                        "${convertTime(it.dt,"MMMM dd, hh:mm")} - ${it.weather.first().description} - ${it.temp.roundToInt()}°C"
                    }
                } else {
                    displayErrorMessage()
                }
            }
        })
    }

    private fun displayUI(visible: Boolean){
        dateTextView.visibility = if (visible) View.VISIBLE else View.GONE
        iconImageView.visibility = if (visible) View.VISIBLE else View.GONE
        descriptionTextView.visibility = if (visible) View.VISIBLE else View.GONE
        tempTextView.visibility = if (visible) View.VISIBLE else View.GONE
        humidityTextView.visibility = if (visible) View.VISIBLE else View.GONE
        dailyButton.visibility = if (visible) View.VISIBLE else View.GONE
        hourlyButton.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun displayProgressBar(visible: Boolean){
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun displayErrorMessage(){
        mainLayout.indefiniteSnackbar("Network Error. Try again later","OK"){
            getWeather()
        }
    }

    private fun setUpWidgets(current: Current?){
        descriptionTextView.text = current?.weather?.first()?.description
        iconImageView.setImageResource(getWeatherIcon(current?.weather?.first()?.main ?:"Clear",current?.weather?.first()?.icon ?:"01d"))

        tempTextView.text = current?.temp?.roundToInt().toString()+"°C"
        humidityTextView.text = current?.humidity?.roundToInt().toString()+"%"

        dateTextView.text = getDateTime()?.capitalize() ?: "No Data"
    }

    private fun getDateTime(): String?{
        return try {
            val simpleDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
            val date = Calendar.getInstance().time
            simpleDateFormat.format(date)
        }catch (e: Exception){
            e.toString()
        }
    }

    private fun getWeatherIcon(main: String, icon: String): Int {
        return when (main) {
            "Clear" -> when (icon) {
                "01d" -> R.drawable.clear_day
                "01n" -> R.drawable.clear_night
                else -> R.drawable.clear_day
            }

            "Snow" -> R.drawable.snow
            "Thunderstorm" -> R.drawable.thunderstorm
            "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Ash", "Squall", "Tornado" -> R.drawable.fog

            "Clouds" -> when (icon) {
                "03d", "03n" -> R.drawable.cloudy
                "02d", "04d" -> R.drawable.partly_cloudy_day
                "02n", "04n" -> R.drawable.partly_cloudy_night
                else -> R.drawable.cloudy
            }

            "Drizzle", "Rain" -> when (icon) {
                "09d", "10d" -> R.drawable.rain_day
                "09n", "10n" -> R.drawable.rain_night
                else -> R.drawable.rain
            }

            else -> R.drawable.clear_day
        }
    }

    fun startDailyActivity(view: View){
        val intent = Intent(this, DailyActivity::class.java)
        startActivity(intent)
    }

    fun startHourlyActivity(view: View){
        val intent = Intent(this, HourlyActivity::class.java)
        val array = hourlySummary?.toTypedArray()
        intent.putExtra("HOURLY_SUMMARY", array)
        startActivity(intent)
    }

    fun convertTime(time: Int, format: String): String{
        val cal = Calendar.getInstance(Locale.getDefault())
        cal.timeInMillis = (time * 1000L)
        val date = DateFormat.format(format, cal).toString().capitalize()
        return date
    }

}