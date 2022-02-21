package com.yaroslavnikolenko.alotrecyclers.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewChat(chat: Chat)

    @Query("SELECT * FROM my_table ORDER BY id ASC")
    fun readAllInfo(): LiveData<List<Chat>>
}