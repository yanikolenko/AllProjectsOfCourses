package com.yaroslavnikolenko.quakereload.api

import com.yaroslavnikolenko.quakereload.constants.GET_QUAKE
import retrofit2.Call
import retrofit2.http.GET

interface QuakeApi {
    @GET(GET_QUAKE)
    fun getData(): Call<AllFieldsApiQuake>
}