package com.yaroslavnikolenko.weatherapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.weatherapplication.R
import com.yaroslavnikolenko.weatherapplication.ui.dailyWeather.DailyWeather1
import com.yaroslavnikolenko.weatherapplication.ui.recycler.MyRecyclerAdapter
import com.yaroslavnikolenko.weatherapplication.ui.twenyFourWeather.FiveDaysWeather

class WeatherToday : Fragment() {

    private val dailyWeather by viewModels<DailyWeather1>()
    private val fiveDaysWeather by viewModels<FiveDaysWeather>()
    lateinit var temp: TextView
    lateinit var description: TextView
    lateinit var wind: TextView
    lateinit var feelTemperature: TextView
    lateinit var date: TextView
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        temp = view.findViewById(R.id.mainTemperatureView)
        description = view.findViewById(R.id.descriptionView)
        wind = view.findViewById(R.id.windView)
        feelTemperature = view.findViewById(R.id.feelTemperatureView)
        date = view.findViewById(R.id.dateView)
        recycler = view.findViewById(R.id.weatherTodayRecyclerView)

        observeUpdate()
        observeUpdate2()
        FiveDaysWeather()

        return view

// Залишив код нижче щоб подивитися і згадати якщо треба буде
//        accessToData2 = DailyWeather()
//
//        val y: (List<String>) -> Unit = { value: List<String> ->
//            MainScope().launch { }
//        }
    }

    private fun observeUpdate() {
        dailyWeather.resultData.observe(requireActivity()){
            temp.text = "${it.tempC.toString()} °C"
            feelTemperature.text = "Feels like: ${it.feelslikeC.toString()} °C"
            description.text = it.condition?.text
            wind.text = "Speed of wind:${it.windKph} km/h"
            date.text = it.lastUpdated
        }
    }

    private fun observeUpdate2(){
        fiveDaysWeather.resultData.observe(requireActivity()){
            val adapter = MyRecyclerAdapter(requireContext(), it)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter

        }
    }


}