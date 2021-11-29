package com.yaroslavnikolenko.alotrecyclers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yaroslavnikolenko.alotrecyclers.Converter

@Database(entities = [Chat::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ChatDataBase: RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object{
        @Volatile
        private var INSTANCE: ChatDataBase? = null

        fun getDatabase(context: Context): ChatDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}