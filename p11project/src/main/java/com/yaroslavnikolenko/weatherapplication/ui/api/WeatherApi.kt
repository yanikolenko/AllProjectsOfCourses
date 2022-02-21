package com.yaroslavnikolenko.weatherapplication.ui.api

import com.yaroslavnikolenko.weatherapplication.ui.CurrentWeatherResponse
import com.yaroslavnikolenko.weatherapplication.ui.TwentyFourWeatherResponse
import com.yaroslavnikolenko.weatherapplication.ui.constants.WeatherConst
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET(WeatherConst.GET_CURRENT_WEATHER)
    fun getDailyData(): Call<CurrentWeatherResponse>

    @GET(WeatherConst.GET_DAY_WEATHER)
    fun getDayData(): Call<TwentyFourWeatherResponse>
}