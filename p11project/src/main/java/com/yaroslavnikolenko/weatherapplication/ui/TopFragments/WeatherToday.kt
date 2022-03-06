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
import com.yaroslavnikolenko.weatherapplication.ui.dailyWeather.DailyWeather
import com.yaroslavnikolenko.weatherapplication.ui.recycler.MyRecyclerAdapter
import com.yaroslavnikolenko.weatherapplication.ui.twenyFourWeather.TwentyFourWeather

class WeatherToday : Fragment() {

    private val dailyWeather by viewModels<DailyWeather>()
    private val fiveDaysWeather by viewModels<TwentyFourWeather>()
    private var temp: TextView? = null
    private var description: TextView? = null
    private var wind: TextView? = null
    private var feelTemperature: TextView? = null
    private var date: TextView? = null
    private var recycler: RecyclerView? = null
    private var adapter: MyRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_weather_today, container, false)

// Залишив код нижче щоб подивитися і згадати якщо треба буде
//        accessToData2 = DailyWeather()
//
//        val y: (List<String>) -> Unit = { value: List<String> ->
//            MainScope().launch { }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        temp = view.findViewById(R.id.mainTemperatureView)
        description = view.findViewById(R.id.descriptionView)
        wind = view.findViewById(R.id.windView)
        feelTemperature = view.findViewById(R.id.feelTemperatureView)
        date = view.findViewById(R.id.dateView)
        recycler = view.findViewById(R.id.weatherTodayRecyclerView)

        observeDailyWeather()
        observeDayWeather()
        TwentyFourWeather()
    }

    private fun observeDailyWeather() {
        dailyWeather.resultData.observe(requireActivity()){
            temp?.text = getString(R.string.temperature_daily, it.tempC.toString())
            feelTemperature?.text = getString(R.string.temperature_feel, it.feelslikeC.toString())
            description?.text = it.condition?.text
            wind?.text = getString(R.string.wind, it.windKph.toString())
            date?.text = it.lastUpdated
        }
    }

    private fun observeDayWeather(){
        fiveDaysWeather.resultData.observe(requireActivity()){
            initAdapter(it)
        }
    }

    private fun initAdapter(it: List<HourItem?>){
        adapter = MyRecyclerAdapter(requireContext(), it)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter
    }


}