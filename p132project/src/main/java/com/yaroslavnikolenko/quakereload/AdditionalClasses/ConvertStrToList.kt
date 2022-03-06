package com.yaroslavnikolenko.quakereload.AdditionalClasses

fun convertStrToList(value: String): List<String> =  listOf(*value.split(",").toTypedArray())
