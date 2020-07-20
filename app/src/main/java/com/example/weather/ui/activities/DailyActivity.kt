package com.example.weather.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.data.model.Daily
import com.example.weather.ui.adapters.DailyAdapter
import kotlinx.android.synthetic.main.activity_daily.*

class DailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        val dailyData: ArrayList<Daily>? = intent.getParcelableArrayListExtra(MainActivity.DAILY_DATA)
        val dailyAdapter = DailyAdapter(dailyData)
        dailyRecyclerView.layoutManager = LinearLayoutManager(this)
        dailyRecyclerView.adapter = dailyAdapter

    }
}