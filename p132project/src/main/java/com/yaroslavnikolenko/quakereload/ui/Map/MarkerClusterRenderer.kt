package com.yaroslavnikolenko.quakereload

import android.content.Context
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.yaroslavnikolenko.quakereload.AdditionalClasses.MyItem
import com.yaroslavnikolenko.quakereload.AdditionalClasses.addTextToBitmap
import com.yaroslavnikolenko.quakereload.AdditionalClasses.chooseMarker
import java.lang.StringBuilder

class MarkerClusterRenderer(val context: Context, mMap: GoogleMap, clusterManager: ClusterManager<MyItem>):
    DefaultClusterRenderer<MyItem>(context, mMap, clusterManager){

    override fun onBeforeClusterItemRendered(
        item: MyItem,
        markerOptions: MarkerOptions
    ) {
        super.onBeforeClusterItemRendered(item, markerOptions)

        markerOptions.icon(BitmapDescriptorFactory.fromResource(chooseMarker(item.title.toDouble()).typeofMarker))
    }

    override fun getDescriptorForCluster(cluster: Cluster<MyItem>): BitmapDescriptor {

        var x = 0.0
        cluster.items.forEach {
            x += it.title.toDouble()
        }



        return when (x / cluster.items.size){
            in 1.0..2.0 -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
            in 2.01..3.0 -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
            in 3.01..4.5 -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
            in 4.51..6.0 -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
            in 6.01..100.0 -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
            else -> {
                BitmapDescriptorFactory.fromBitmap(addTextToBitmap(context, rounding(x / cluster.items.size)))
            }
        }

        //println(x / cluster.items.size)



    }

    fun rounding(value: Double): Double = String.format("%.1f", value).replace(",", ".").toDouble()


}