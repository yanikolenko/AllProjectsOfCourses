package com.yaroslavnikolenko.weatherapplication.ui.twenyFourWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.weatherapplication.ui.HourItem
import com.yaroslavnikolenko.weatherapplication.ui.Location
import com.yaroslavnikolenko.weatherapplication.ui.api.WeatherApi
import com.yaroslavnikolenko.weatherapplication.ui.TwentyFourWeatherResponse
import com.yaroslavnikolenko.weatherapplication.ui.constants.WeatherConst
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TwentyFourWeather: ViewModel() {

    private val _resultData = MutableLiveData<List<HourItem?>>()
    val resultData: LiveData<List<HourItem?>> = _resultData

    private val _resultLocation = MutableLiveData<Location>()
    val resultLocation: LiveData<Location> = _resultLocation

    init {
        getData()
    }

    private fun getData() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WeatherConst.BASE_URL)
            .build()
            .create(WeatherApi::class.java)

        val retrofitData = retrofit.getDayData()
        retrofitData.enqueue(object : Callback<TwentyFourWeatherResponse?> {
            override fun onResponse(
                call: Call<TwentyFourWeatherResponse?>,
                response: retrofit2.Response<TwentyFourWeatherResponse?>
            ) {
                val body = response.body()
                val firstStep = body?.forecast?.forecastday!![0]
                val secondStep = firstStep?.hour
                _resultData.value = secondStep!!
                println(body.location?.country)
                _resultLocation.value = body.location!!
            }

            override fun onFailure(call: Call<TwentyFourWeatherResponse?>, t: Throwable) {}
        })

    }
}