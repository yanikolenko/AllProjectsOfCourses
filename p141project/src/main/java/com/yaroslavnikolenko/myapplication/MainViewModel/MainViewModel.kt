package com.yaroslavnikolenko.myapplication.MainViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.myapplication.Converter.ConverterTaskList
import com.yaroslavnikolenko.myapplication.data.db.model.UserEntity
import com.yaroslavnikolenko.myapplication.data.repository.UserRepository
import java.util.ArrayList

class MainViewModel: ViewModel() {

    companion object{
        var numberOfCheckedList = MutableLiveData<Int>()
    }

    private val userRepository = UserRepository()

    val mylist = ArrayList<String>()
    val infoToRecycler =  MutableLiveData(mylist)

    val taskStatus = ArrayList<String>()
    val taskStatusToRecycler =  MutableLiveData(taskStatus)

    fun addNewList(id: Int, nameOfList: String, time: String) {
        userRepository.addUser(UserEntity(id, nameOfList, time, ConverterTaskList.fromListToString(infoToRecycler.value!!), ConverterTaskList.fromListToString(taskStatusToRecycler.value!!)))
    }

    fun changeList(id: Int, nameOfList: String, time: String){
        userRepository.changeList(UserEntity(id, nameOfList, time, ConverterTaskList.fromListToString(infoToRecycler.value!!), ConverterTaskList.fromListToString(taskStatusToRecycler.value!!)))
    }

    fun getListForId(id: Int) = userRepository.getListForId(id)

    fun deleteById(id: Int){ userRepository.deleteById(id) }

    fun getAllLists() = userRepository.getUser()

}