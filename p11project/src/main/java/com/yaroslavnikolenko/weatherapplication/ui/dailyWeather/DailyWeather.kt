package com.yaroslavnikolenko.weatherapplication.ui.dailyWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.weatherapplication.ui.Current
import com.yaroslavnikolenko.weatherapplication.ui.CurrentWeatherResponse
import com.yaroslavnikolenko.weatherapplication.ui.api.WeatherApi
import com.yaroslavnikolenko.weatherapplication.ui.constants.WeatherConst
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailyWeather: ViewModel() {

    private val _resultData = MutableLiveData<Current>()
    val resultData: LiveData<Current> = _resultData

    init {
        getData()
    }

    private fun getData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WeatherConst.BASE_URL)
            .build()
            .create(WeatherApi::class.java)

        val retrofitData = retrofit.getDailyData()
        retrofitData.enqueue(object : Callback<CurrentWeatherResponse?> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse?>,
                response: retrofit2.Response<CurrentWeatherResponse?>
            ) {
                val body = response.body()?.current
                _resultData.value = body!!

            }

            override fun onFailure(call: Call<CurrentWeatherResponse?>, t: Throwable) {

            }
        })
    }
}