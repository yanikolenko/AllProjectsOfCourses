package com.yaroslavnikolenko.quakereload.AdditionalClasses

import androidx.annotation.ColorRes
import com.yaroslavnikolenko.quakereload.R

fun getLevel(value: Double): Level{
    return when(value){
        in 1.0..2.0 -> {
            Level("Ледь відчутний", R.color.green)
        }
        in 2.01..3.0 -> {
            Level("Слабкий", R.color.light_green)
        }
        in 3.01..4.5 -> {
            Level("Середній", R.color.yellow)
        }
        in 4.51..6.0 -> {
            Level("Сильний", R.color.orange)
        }
        in 6.01..100.0 -> {
            Level("Дуже сильний", R.color.red)
        }else -> {
            Level("Екстремально", R.color.black)
        }
    }
}

data class Level(
    val title: String,
    @ColorRes
    val backgroundColor: Int
)