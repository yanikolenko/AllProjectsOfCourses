package com.yaroslavnikolenko.myapplication.additionalClasses

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class CountDaysAgo(private val chooseMonth: Int, private val chooseDay: Int) {

    private val months = listOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    var firstValue = chooseDay
    var secondValue = 0
    var i = 0
    var j = 0

    private val date = Calendar.getInstance().time
    private val month = SimpleDateFormat("MM", Locale.getDefault())
    private val day = SimpleDateFormat("dd", Locale.getDefault())
    private val formatedMonth = month.format(date).toInt()
    private val formatedDay= day.format(date).toInt()

    fun diff(): String{
        while (i < chooseMonth){
            firstValue += months[i]
            i++
        }

        while (j < formatedMonth){
            secondValue += months[j]
            j++
        }

        return when(abs((secondValue + formatedDay) - firstValue)){
            0 -> "Today"
            else -> abs((secondValue + formatedDay) - firstValue).toString()
        }
    }


}