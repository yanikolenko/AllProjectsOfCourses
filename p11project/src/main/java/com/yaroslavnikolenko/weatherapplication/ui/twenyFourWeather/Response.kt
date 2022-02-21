package com.yaroslavnikolenko.weatherapplication.ui

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class Weather(

	@SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@SerializedName("description")
	val description: String? = null
)

data class DataItem(

	@SerializedName("sunrise")
	val sunrise: String? = null,

	@field:SerializedName("pod")
	val pod: String? = null,

	@field:SerializedName("pres")
	val pres: Int? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("ob_time")
	val obTime: String? = null,

	@field:SerializedName("wind_cdir")
	val windCdir: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("clouds")
	val clouds: Int? = null,

	@field:SerializedName("wind_spd")
	val windSpd: Double? = null,

	@field:SerializedName("city_name")
	val cityName: String? = null,

	@field:SerializedName("h_angle")
	val hAngle: Int? = null,

	@field:SerializedName("datetime")
	val datetime: String? = null,

	@field:SerializedName("precip")
	val precip: Int? = null,

	@field:SerializedName("weather")
	val weather: Weather? = null,

	@field:SerializedName("station")
	val station: String? = null,

	@field:SerializedName("elev_angle")
	val elevAngle: Double? = null,

	@field:SerializedName("dni")
	val dni: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,
	@field:SerializedName("vis")
	val vis: Int? = null,

	@field:SerializedName("uv")
	val uv: Double? = null,

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("dhi")
	val dhi: Double? = null,

	@field:SerializedName("ghi")
	val ghi: Double? = null,

	@field:SerializedName("app_temp")
	val appTemp: Double? = null,

	@field:SerializedName("dewpt")
	val dewpt: Double? = null,

	@field:SerializedName("wind_dir")
	val windDir: Int? = null,

	@field:SerializedName("solar_rad")
	val solarRad: Double? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("rh")
	val rh: Double? = null,

	@field:SerializedName("slp")
	val slp: Int? = null,

	@field:SerializedName("snow")
	val snow: Int? = null,

	@field:SerializedName("sunset")
	val sunset: String? = null,

	@field:SerializedName("aqi")
	val aqi: Int? = null,

	@field:SerializedName("state_code")
	val stateCode: String? = null,

	@field:SerializedName("wind_cdir_full")
	val windCdirFull: String? = null,

	@field:SerializedName("ts")
	val ts: Int? = null
)
