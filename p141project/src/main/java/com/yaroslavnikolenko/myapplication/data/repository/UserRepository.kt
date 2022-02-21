package com.yaroslavnikolenko.myapplication.data.repository

import com.yaroslavnikolenko.myapplication.data.db.AppDataBase
import com.yaroslavnikolenko.myapplication.data.db.model.UserEntity

class UserRepository {

    private val db = AppDataBase.getInstance()
    private val userDao = db?.getUserDao()

    fun getUser() = userDao?.getAll().orEmpty()

    fun getListForId(id: Int) = userDao?.getListForId(id)

    fun addUser(userEntity: UserEntity) { userDao?.insert(userEntity) }

    fun changeList(userEntity: UserEntity){ userDao?.update(userEntity) }

    fun deleteById(id: Int){ userDao?.deleteById(id) }
}