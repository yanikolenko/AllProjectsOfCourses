package com.yaroslavnikolenko.myapplication.realtimedatabase.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.yaroslavnikolenko.myapplication.context.App
import com.yaroslavnikolenko.myapplication.realtimedatabase.data.ToDoListData

class ToDoListRepository {

    var firebase: DatabaseReference? = null
    private var toDoItemList: MutableList<ToDoListData>? = null
    private var toDoItemNewList: MutableList<ToDoListData>? = null
    private val mutableLiveData = MutableLiveData<MutableList<ToDoListData>>()
    private val mutableLiveDataNew = MutableLiveData<ToDoListData>()

    init {
        initFirebase()
    }

    private fun initFirebase(){
        firebase = FirebaseDatabase.getInstance().reference
    }

    val itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {

            addDataToList(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Toast.makeText(App.context, "Problem with Firebase !", Toast.LENGTH_SHORT).show()
        }
    }

    val itemListener1: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {

            addDataToList1(dataSnapshot)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Toast.makeText(App.context, "Problem with Firebase !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot){
        toDoItemList = mutableListOf()
        dataSnapshot.children.forEach {
            val hashMapData = it.value as HashMap<*, *>

            val todoItem = ToDoListData()

            todoItem.objectId = it.key
            todoItem.done = hashMapData["done"] as Boolean?
            todoItem.nameOfList = hashMapData["nameOfList"] as String?
            todoItem.tasks = hashMapData["tasks"] as List<List<String>>?
            todoItem.time = hashMapData["time"] as String?

            toDoItemList?.add(todoItem)
            println(todoItem)

        }
        mutableLiveData.value = toDoItemList
    }

    private fun addDataToList1(dataSnapshot: DataSnapshot){
        toDoItemNewList = mutableListOf()
        val x = mutableListOf<Any>()
        val todoItem = ToDoListData()

        dataSnapshot.children.forEach {
            x.add(it.value!!)
        }
        todoItem.done = x[0] as Boolean
        todoItem.nameOfList = x[1] as String
        todoItem.objectId = x[2] as String
        todoItem.tasks = x[3] as ArrayList<List<String>>
        todoItem.time = x[4] as String

        mutableLiveDataNew.value = todoItem
    }

    fun getDataFromDB(): MutableLiveData<MutableList<ToDoListData>>{
        firebase?.orderByKey()?.addListenerForSingleValueEvent(itemListener)
        return mutableLiveData
    }

    fun getDataFormDBById(id: String): MutableLiveData<ToDoListData>{
        firebase?.child(id)?.addListenerForSingleValueEvent(itemListener1)
        return mutableLiveDataNew
    }

    fun addDataToDB(done: Boolean, nameOfList: String, tasks: List<List<String>>, time: String){
        val newItem = firebase?.push()
        newItem?.setValue(ToDoListData(newItem.key, done, nameOfList, tasks, time))
    }

    fun updateDataInDB(id: String, done: Boolean, nameOfList: String, tasks: List<List<String>>, time: String){
        firebase?.child(id)?.setValue(ToDoListData(id, done, nameOfList, tasks, time))
    }

    fun deleteDataOfDB(id: String){
        firebase?.child(id)?.removeValue()
    }
}