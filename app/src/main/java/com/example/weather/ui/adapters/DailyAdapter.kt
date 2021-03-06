package com.example.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.Daily
import com.example.weather.ui.convertTime
import kotlinx.android.synthetic.main.list_item.view.*
import kotlin.math.roundToInt

class DailyAdapter(private val data: List<Daily>?) :
    RecyclerView.Adapter<DailyAdapter.DailyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return DailyHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: DailyHolder, position: Int) {
        data?.let {
            holder.bind(it[position])
        }
    }

    inner class DailyHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: Daily) = with(view){
            dailyDateTextView.text = convertTime(data.dt, "EEEE MMMM dd")
            var resume = "${data.weather.first().description} " +
                    "- Max: " + "${data.temp?.max?.roundToInt().toString()}°C " +
                    "- Min: " + "${data.temp?.min?.roundToInt().toString()}°C "
            dailySummaryTextView.text = resume
        }
    }
}