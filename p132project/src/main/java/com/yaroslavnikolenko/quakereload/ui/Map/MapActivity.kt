package com.yaroslavnikolenko.quakereload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.clustering.ClusterManager
import com.yaroslavnikolenko.quakereload.AdditionalClasses.*
import com.yaroslavnikolenko.quakereload.api.QuakeGetData
import com.yaroslavnikolenko.quakereload.databinding.ActivityMapBinding
import com.yaroslavnikolenko.quakereload.api.FeaturesItem

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var activityMapBinding: ActivityMapBinding? = null
    private var mMap: GoogleMap? = null
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var viewModel: QuakeGetData? = null
    var allFieldsApiQuake = mutableListOf<FeaturesItem?>()
    var mapFragment: SupportMapFragment? = null
    var markerClusterRenderer: MarkerClusterRenderer? = null

    private var flag = false

    private var array: List<String>? = null
    private var prevValue: MyItem? = null
    private var infoAboutCheckedMarker: LatLng? = null
    private var clusterManager: ClusterManager<MyItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMapBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMapBinding?.root)

        getInfoFromQuakeApi()
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet))

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        getInfoFromQuakeApi()
        clusterManager = ClusterManager(this, mMap)
        mMap?.setOnCameraIdleListener(clusterManager)
        mMap?.setOnMarkerClickListener(clusterManager)

        markerClusterRenderer = MarkerClusterRenderer(this, googleMap, clusterManager!!)

        addMarkers()

        clusterManager?.setOnClusterItemClickListener {
            flag = true
            showHideBottomSheet(false)
            setInfoToBottomSheet(convertStrToList(it.snippet))

            if (array.isNullOrEmpty()){
                array = convertStrToList(it.snippet)
                changeCheckedMarker(it)
                prevValue = it
            }else{
                if (it != prevValue){
                    changeCheckedMarker(it)
                    prevValue = it
                    array = null
                }
            }
            true
        }

        MapListeners()
    }

    fun changeCheckedMarker(value: MyItem){
        markerClusterRenderer!!.getMarker(prevValue)
            ?.setIcon(BitmapDescriptorFactory.fromResource(chooseMarker(prevValue?.title!!.toDouble()).typeofMarker))

        markerClusterRenderer!!.getMarker(value)
            ?.setIcon(BitmapDescriptorFactory.fromResource(chooseMarkerBack(value.title.toDouble()).typeofMarker))
        infoAboutCheckedMarker = LatLng(value.position.latitude, value.position.longitude)
    }

    fun MapListeners(){
        mMap?.setOnCameraMoveListener {
            if (prevValue != null){
                markerClusterRenderer!!.getMarker(prevValue)
                    ?.setIcon(BitmapDescriptorFactory.fromResource(chooseMarkerBack(prevValue?.title!!.toDouble()).typeofMarker))
            }

            if (flag){
                showHideBottomSheet(true)
            }
        }

        mMap?.setOnCameraIdleListener {

            if (prevValue != null){
                markerClusterRenderer!!.getMarker(prevValue)
                    ?.setIcon(BitmapDescriptorFactory.fromResource(chooseMarkerBack(prevValue?.title!!.toDouble()).typeofMarker))
            }
            clusterManager?.onCameraIdle()

            if (flag){
                showHideBottomSheet(false)
            }
        }
    }

    fun addMarkers(){
        for (i in allFieldsApiQuake){

            val infoToBottomSheet = "${i?.properties?.magnitude}, ${i?.properties?.locality}, ${i?.properties?.time!!}, ${i.geometry?.coordinates!![1]!!}, ${i.geometry.coordinates[0]!!}"

            clusterManager?.renderer = markerClusterRenderer
            clusterManager?.addItem(MyItem(i.geometry.coordinates[1]!!, i.geometry.coordinates[0]!!, "${i.properties.magnitude}", infoToBottomSheet))

            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(i.geometry.coordinates[1]!!, i.geometry.coordinates[0]!!), 8f))

            setInfoToBottomSheet(convertStrToList(infoToBottomSheet))
            clusterManager?.cluster()
        }
    }

    fun showHideBottomSheet(value: Boolean){
        if(value){
            bottomSheetBehavior?.isHideable = true
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        }else{
            bottomSheetBehavior?.isHideable = false
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun getInfoFromQuakeApi(){
        viewModel = ViewModelProvider(this).get(QuakeGetData::class.java)
            viewModel?.allFieldsOfQuakeApi?.observe(this, Observer {
                setApiQuakeList(it)
                initMap()
                activityMapBinding?.mapProgressBar?.visibility = View.INVISIBLE
            })
    }

    fun setApiQuakeList(allFieldsApiQuake: List<FeaturesItem?>){
        this.allFieldsApiQuake = allFieldsApiQuake.toMutableList()
    }

    fun initMap(){

        if (mapFragment == null){
            mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment?.getMapAsync(this)
        }
    }

    fun setInfoToBottomSheet(value: List<String>){

        // value[0] - magnitude
        // value[1] - locality
        // value[2] - time

        activityMapBinding?.mapLocationView?.text = value[1]
        activityMapBinding?.mapSensitiveView?.text = getLevel(value[0].toDouble()).title
        activityMapBinding?.mapSensitiveView?.
        setBackgroundColor(activityMapBinding?.root?.context!!.getColor(getLevel(value[0].toDouble()).backgroundColor))
        activityMapBinding?.mapTimeView?.text = CountDaysFromDate(value[2]).diff().title
        activityMapBinding?.mapWaveView?.text = String.format("%.1f", value[0].toDouble())
    }
}