package com.yaroslavnikolenko.myapplication

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("features")
	val features: List<FeaturesItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Geometry(

	@field:SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Properties(

	@field:SerializedName("depth")
	val depth: Double? = null,

	@field:SerializedName("mmi")
	val mmi: Int? = null,

	@field:SerializedName("locality")
	val locality: String? = null,

	@field:SerializedName("magnitude")
	val magnitude: Double? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("publicID")
	val publicID: String? = null,

	@field:SerializedName("quality")
	val quality: String? = null
)

data class FeaturesItem(

	@field:SerializedName("geometry")
	val geometry: Geometry? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("properties")
	val properties: Properties? = null
)
