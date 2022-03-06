package com.yaroslavnikolenko.quakereload.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.quakereload.constants.BASE_URL
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuakeGetData: ViewModel() {

    val allFieldsOfQuakeApi = MutableLiveData<List<FeaturesItem?>>()

    var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(QuakeApi::class.java)

    init {
        getData()
    }

    fun getData() {

        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : retrofit2.Callback<AllFieldsApiQuake?> {
            override fun onResponse(
                call: Call<AllFieldsApiQuake?>,
                response: retrofit2.Response<AllFieldsApiQuake?>
            ) {
                val body = response.body()?.features

                allFieldsOfQuakeApi.postValue(body)

            }

            override fun onFailure(call: Call<AllFieldsApiQuake?>, t: Throwable) {
                println("!!! Problem with API !!!")
            }
        })

    }
}