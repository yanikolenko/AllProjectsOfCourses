package com.yaroslavnikolenko.myapplication.additionalClasses

import android.content.Context
import android.widget.TextView
import com.yaroslavnikolenko.myapplication.R

class ChooseText(val context: Context) {
    fun chooseText(value: Double, text: TextView){
        when(value){
            in 1.0..2.0 -> {
                text.text = "Ледь відчутний"
                text.setBackgroundColor(context.getColor(R.color.back_sensitive1))
            }
            in 2.1..3.0 -> {
                text.text = "Слабкий"
                text.setBackgroundColor(context.getColor(R.color.back_sensitive2))
            }
            in 3.1..4.5 -> {
                text.text = "Середній"
                text.setBackgroundColor(context.getColor(R.color.back_sensitive3))
            }
            in 4.6..6.0 -> {
                text.text = "Сильний"
                text.setBackgroundColor(context.getColor(R.color.back_sensitive4))
            }
            in 6.1..100.0 -> {
                text.text = "Дуже сильний"
                text.setBackgroundColor(context.getColor(R.color.back_sensitive5))
            }
        }
    }
}