package com.yaroslavnikolenko.weatherapplication.ui.twenyFourWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.weatherapplication.ui.HourItem
import com.yaroslavnikolenko.weatherapplication.ui.Location
import com.yaroslavnikolenko.weatherapplication.ui.MyApi1
import com.yaroslavnikolenko.weatherapplication.ui.Response2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FiveDaysWeather: ViewModel() {

    private val _resultData = MutableLiveData<List<HourItem?>>()
    val resultData: LiveData<List<HourItem?>> = _resultData

    private val _resultLocation = MutableLiveData<Location>()
    val resultLocation: LiveData<Location> = _resultLocation

    init {
        getData()
    }

    fun getData() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.weatherapi.com/v1/")
            .build()
            .create(MyApi1::class.java)

        val retrofitData = retrofit.getData2()
        retrofitData.enqueue(object : Callback<Response2?> {
            override fun onResponse(
                call: Call<Response2?>,
                response: retrofit2.Response<Response2?>
            ) {
                val body = response.body()
                val firstStep = body?.forecast?.forecastday!![0]
                val secondStep = firstStep?.hour
                _resultData.value = secondStep!!
                println(body.location?.country)
                _resultLocation.value = body.location!!
            }

            override fun onFailure(call: Call<Response2?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}