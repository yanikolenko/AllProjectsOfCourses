package com.yaroslavnikolenko.quakereload.ui.Map

import androidx.annotation.DrawableRes
import com.yaroslavnikolenko.quakereload.R

fun chooseMarker(value: Double): Marker {
    return when(value){
        in 1.0..2.0 -> {
            Marker(R.drawable.green_marker)
        }
        in 2.01..3.0 -> {
            Marker(R.drawable.light_green_marker)
        }
        in 3.01..4.5 -> {
            Marker(R.drawable.yellow_marker)
        }
        in 4.51..6.0 -> {
            Marker(R.drawable.orange_marker)
        }
        in 6.01..100.0 -> {
            Marker(R.drawable.red_marker)
        }
        else -> {
            Marker(R.drawable.red_marker)
        }
    }
}

fun chooseMarkerBack(value: Double): Marker {
    return when(value){
        in 1.0..2.0 -> {
            Marker(R.drawable.green_back_of_checked)
        }
        in 2.01..3.0 -> {
            Marker(R.drawable.light_green_back_of_checked)
        }
        in 3.01..4.5 -> {
            Marker(R.drawable.yellow_back_of_checked)
        }
        in 4.51..6.0 -> {
            Marker(R.drawable.orange_back_of_checked)
        }
        in 6.01..100.0 -> {
            Marker(R.drawable.red_back_of_checked)
        }
        else -> {
            Marker(R.drawable.red_back_of_checked)
        }
    }
}

data class Marker(
    @DrawableRes
    val typeofMarker: Int
)