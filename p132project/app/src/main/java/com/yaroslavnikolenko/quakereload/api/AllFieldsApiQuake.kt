package com.yaroslavnikolenko.quakereload.api

import com.google.gson.annotations.SerializedName

data class AllFieldsApiQuake(

    @SerializedName("features")
    val features: List<FeaturesItem?>? = null,

    @SerializedName("type")
    val type: String? = null
)

data class Geometry(

    @SerializedName("coordinates")
    val coordinates: List<Double?>? = null,

    @SerializedName("type")
    val type: String? = null
)

data class Properties(

    @SerializedName("depth")
    val depth: Double? = null,

    @SerializedName("mmi")
    val mmi: Int? = null,

    @SerializedName("locality")
    val locality: String? = null,

    @SerializedName("magnitude")
    val magnitude: Double? = null,

    @SerializedName("time")
    val time: String? = null,

    @SerializedName("publicID")
    val publicID: String? = null,

    @SerializedName("quality")
    val quality: String? = null
)

data class FeaturesItem(

    @SerializedName("geometry")
    val geometry: Geometry? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("properties")
    val properties: Properties? = null
)
