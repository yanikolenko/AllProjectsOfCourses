package com.yaroslavnikolenko.myapplication.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yaroslavnikolenko.myapplication.GlobalContext.App
import com.yaroslavnikolenko.myapplication.data.db.dao.UserDao
import com.yaroslavnikolenko.myapplication.data.db.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object{

        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase? {
            if (instance == null) {
                synchronized(AppDataBase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            App.context!!.applicationContext,
                            AppDataBase::class.java, "my_room"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance
        }
    }
}