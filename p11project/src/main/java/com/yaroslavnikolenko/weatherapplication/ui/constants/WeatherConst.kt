package com.yaroslavnikolenko.weatherapplication.ui.constants

class WeatherConst {
    companion object{
        const val BASE_URL = "https://api.weatherapi.com/v1/"
        const val GET_CURRENT_WEATHER = "current.json?key=b541956016a643ec923110022211212&q=London&aqi=no"
        const val GET_DAY_WEATHER = "forecast.json?key=b541956016a643ec923110022211212&q=London&days=1&aqi=no&alerts=no"

    }
}