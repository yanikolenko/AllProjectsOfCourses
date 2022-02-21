package com.yaroslavnikolenko.myapplication.Converter

import androidx.room.TypeConverter
import java.util.stream.Collectors

class ConverterTaskList {

    companion object{

        @TypeConverter
        fun toTaskList(tasks: String): MutableList<Array<String>> {
            return mutableListOf(tasks.split(",".toRegex()).toTypedArray())
        }

        @TypeConverter
        fun fromListToString(status: List<String?>): String {
            return status.stream().collect(Collectors.joining(","))
        }

        @TypeConverter
        fun toCompleteList(status: String): List<String> {
            return status.split(",".toRegex())
        }
    }
}