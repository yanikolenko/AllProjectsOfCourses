package com.yaroslavnikolenko.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val liveDataTitle = MutableLiveData<String>()
    val liveDataSubTitle = MutableLiveData<String>()
    val liveDataImg = MutableLiveData<Int>()
    val liveDataBoolean = MutableLiveData<Boolean>()

    init {
        if (liveDataBoolean.value == null){
            liveDataBoolean.value = true
        }
    }


}