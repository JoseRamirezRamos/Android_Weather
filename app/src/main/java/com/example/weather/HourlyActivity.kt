package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.internal.bind.ArrayTypeAdapter
import kotlinx.android.synthetic.main.activity_hourly.*

class HourlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly)

        val summary = intent.getStringArrayListExtra("HOURLY_SUMMARY")

        val TAG = "HourlyActivity"
        Log.i(TAG, "AQUI --> ${summary}")

        /*val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, summary)
        hourlyWeatherList.adapter = adapter*/

    }
}