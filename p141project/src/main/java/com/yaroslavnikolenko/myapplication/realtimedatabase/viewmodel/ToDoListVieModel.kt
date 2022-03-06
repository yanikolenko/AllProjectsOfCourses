package com.yaroslavnikolenko.myapplication.realtimedatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaroslavnikolenko.myapplication.realtimedatabase.data.ToDoListData
import com.yaroslavnikolenko.myapplication.realtimedatabase.repository.ToDoListRepository

class ToDoListViewModel: ViewModel() {
    private val toDoListDataSource = ToDoListRepository()

    val _listTasks =  MutableLiveData(ArrayList<List<String>>())

    fun getDataFromDB(): LiveData<MutableList<ToDoListData>> {
        return  toDoListDataSource.getDataFromDB()
    }

    fun getDataFromDBById(id: String): MutableLiveData<ToDoListData>{
        return toDoListDataSource.getDataFormDBById(id)
    }

    fun addDataToDB(done: Boolean, nameOfList: String, time: String){
        toDoListDataSource.addDataToDB(done, nameOfList, _listTasks.value!!, time)
    }

    fun updateDataInDb(id: String, done: Boolean, nameOfList: String, time: String){
        toDoListDataSource.updateDataInDB(id, done, nameOfList, _listTasks.value!!, time)
    }

    fun deleteDataOfDB(id: String){
        toDoListDataSource.deleteDataOfDB(id)
    }
}