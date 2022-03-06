package com.yaroslavnikolenko.myapplication.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.myapplication.FeaturesItem

abstract class AbstractQuakeVM: ViewModel() {
    abstract val _resultData: MutableLiveData<List<FeaturesItem?>>
    abstract val resultData: LiveData<List<FeaturesItem?>>

    abstract fun getData()
}