package com.yaroslavnikolenko.myapplication.data.db.dao

import androidx.room.*
import com.yaroslavnikolenko.myapplication.data.db.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM tasksTable")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM tasksTable WHERE id = :id")
    fun getListForId(id:Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(userEntity: UserEntity)

    @Query("DELETE FROM tasksTable WHERE id = :id")
    fun deleteById(id: Int)

    @Query("UPDATE tasksTable SET complete=:list WHERE complete = :id")
    fun update(list: String, id: Int)
}