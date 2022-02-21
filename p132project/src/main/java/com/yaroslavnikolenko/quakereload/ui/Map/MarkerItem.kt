package com.yaroslavnikolenko.quakereload.AdditionalClasses

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MyItem(
    lat: Double,
    lng: Double,
    title: String,
    infoAboutQuake: String
) : ClusterItem {

    private val position: LatLng
    private val title: String
    private val infoAboutQuake: String

    override fun getPosition(): LatLng = position
    override fun getTitle(): String = title
    override fun getSnippet(): String = infoAboutQuake

    init {
        position = LatLng(lat, lng)
        this.title = title
        this.infoAboutQuake = infoAboutQuake
    }

}