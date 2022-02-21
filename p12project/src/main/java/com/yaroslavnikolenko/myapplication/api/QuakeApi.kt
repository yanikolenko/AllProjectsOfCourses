package com.yaroslavnikolenko.myapplication.api

import com.yaroslavnikolenko.myapplication.Response
import com.yaroslavnikolenko.myapplication.constants.QuakeApiConsts
import retrofit2.Call
import retrofit2.http.GET

interface QuakeApi {

    @GET(QuakeApiConsts.GET_QUAKE)
    fun getData(): Call<Response>
}