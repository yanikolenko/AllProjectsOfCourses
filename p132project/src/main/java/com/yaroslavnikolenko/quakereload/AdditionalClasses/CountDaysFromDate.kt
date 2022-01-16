package com.yaroslavnikolenko.quakereload.AdditionalClasses

import com.yaroslavnikolenko.quakereload.constants.TIME_STANDART
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class CountDaysFromDate(dateFromApi: String) {

    private val months = listOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    private val sdf = SimpleDateFormat(TIME_STANDART, Locale.getDefault())

    private val month = SimpleDateFormat("MM", Locale.getDefault())
    private val day = SimpleDateFormat("dd", Locale.getDefault())
    private val year = SimpleDateFormat("yyyy", Locale.getDefault())

    val dayFromApi = sdf.parse(dateFromApi)
    val monthFromApi = sdf.parse(dateFromApi)
    val yearFromApi = sdf.parse(dateFromApi)

    val formattedDayFromApi = day.format(dayFromApi).toInt()
    val formattedMonthFromApi = month.format(monthFromApi).toInt()
    val formattedYearFromApi = year.format(yearFromApi).toInt()

    var firstValue = formattedDayFromApi
    var secondValue = 0
    var i = 0
    var j = 0

    private val currentDate = Calendar.getInstance().time

    private val currentMonth = month.format(currentDate).toInt()
    private val currentDay = day.format(currentDate).toInt()
    private val currentYear = year.format(currentDate).toInt()

    fun diff(): CountDays{

        if (currentYear == formattedYearFromApi){
            while (i < formattedMonthFromApi){
                firstValue += months[i]
                i++
            }

            while (j < currentMonth){
                secondValue += months[j]
                j++
            }
        }else{
            while (i < formattedMonthFromApi){
                firstValue += months[i]
                i++
            }

            while (j < 13* abs(currentYear - formattedYearFromApi)){
                secondValue += months[j]
                j++
            }
        }

        return when(abs((secondValue + currentDay) - firstValue)){
            0 -> {
                CountDays("Сьогодні")
            }
            else -> {
                (CountDays(correctNameOfDays(abs((secondValue + currentDay) - firstValue))))
            }
        }
    }

    fun correctNameOfDays(day: Int): String{
        return when(day){
            1, 21, 31 -> "$day день тому"
            in 2..4, in 22..24 -> "$day дні тому"
            in 5..20, in 25..30 -> "$day днів тому"
            else -> "$day днів тому"
        }
    }

}

data class CountDays(
    val title: String
)