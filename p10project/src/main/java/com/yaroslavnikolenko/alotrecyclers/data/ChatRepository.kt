package com.yaroslavnikolenko.alotrecyclers.data

import androidx.lifecycle.LiveData

class ChatRepository(private val chatDao: ChatDao) {
    val readAllData: LiveData<List<Chat>> =chatDao.readAllInfo()

    suspend fun addNewChat(chat: Chat){
        chatDao.addNewChat(chat)
    }
}