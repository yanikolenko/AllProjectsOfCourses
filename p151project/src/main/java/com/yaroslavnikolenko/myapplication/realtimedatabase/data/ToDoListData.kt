package com.yaroslavnikolenko.myapplication.realtimedatabase.data

data class ToDoListData(
    var objectId: String? = null,
    var done: Boolean? = false,
    var nameOfList: String? = null,
    var tasks: List<List<String>>? = null,
    var time: String? = null
)