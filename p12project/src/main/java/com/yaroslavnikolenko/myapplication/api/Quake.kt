package com.yaroslavnikolenko.myapplication.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yaroslavnikolenko.myapplication.FeaturesItem
import com.yaroslavnikolenko.myapplication.Response
import com.yaroslavnikolenko.myapplication.constants.QuakeApiConsts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Quake: AbstractQuakeVM(){
    override val _resultData: MutableLiveData<List<FeaturesItem?>> = MutableLiveData<List<FeaturesItem?>>()
    override val resultData: LiveData<List<FeaturesItem?>> = _resultData

    var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(QuakeApiConsts.BASE_URL)
        .build()
        .create(QuakeApi::class.java)

    init {
        getData()
    }

    override fun getData() {

        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : retrofit2.Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            ) {
                val body = response.body()?.features
                _resultData.value = body!!

            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {

            }
        })

    }

}
