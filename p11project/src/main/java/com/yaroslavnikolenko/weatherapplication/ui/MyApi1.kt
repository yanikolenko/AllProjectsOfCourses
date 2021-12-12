package com.yaroslavnikolenko.weatherapplication.ui

import retrofit2.Call
import retrofit2.http.GET

interface MyApi1 {
    @GET("current.json?key=b541956016a643ec923110022211212&q=London&aqi=no")
    fun getData(): Call<Response1>

    @GET("forecast.json?key=b541956016a643ec923110022211212&q=London&days=1&aqi=no&alerts=no")
    fun getData2(): Call<Response2>
}