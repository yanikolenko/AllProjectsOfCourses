package com.yaroslavnikolenko.weatherapplication.ui.dailyWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.weatherapplication.ui.Current
import com.yaroslavnikolenko.weatherapplication.ui.MyApi1
import com.yaroslavnikolenko.weatherapplication.ui.Response1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailyWeather1: ViewModel() {

    private val _resultData = MutableLiveData<Current>()
    val resultData: LiveData<Current> = _resultData

    init {
        getData()
    }

    fun getData(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.weatherapi.com/v1/")
            .build()
            .create(MyApi1::class.java)

        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : Callback<Response1?> {
            override fun onResponse(
                call: Call<Response1?>,
                response: retrofit2.Response<Response1?>
            ) {
                val body = response.body()?.current
                _resultData.value = body!!

            }

            override fun onFailure(call: Call<Response1?>, t: Throwable) {

            }
        })
    }
}