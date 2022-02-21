package com.yaroslavnikolenko.alotrecyclers.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val massage: String,
    val img: Bitmap,
    ) {

}