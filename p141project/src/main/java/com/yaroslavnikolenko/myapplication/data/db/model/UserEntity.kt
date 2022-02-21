package com.yaroslavnikolenko.myapplication.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasksTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val nameOfList: String,
    val time: String,
    val taskArray: String,
    val complete: String
) {
}