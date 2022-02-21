//package com.yaroslavnikolenko.p81project
//
//import android.content.ActivityNotFoundException
//import android.content.Intent
//import android.graphics.Bitmap
//import android.media.Image
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.fragment.app.DialogFragment
//
//class MyCustomDialog: DialogFragment() {
//
//    var uri: Uri? = null
//
//    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        println("ppp")
//        this.uri = uri
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_dialog_corner);
//        return inflater.inflate(R.layout.custom_dialog, container, false)
//
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
//        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
//
//        val goToGallary = dialog!!.findViewById<Button>(R.id.goToGallary)
//        val goToCamera = dialog!!.findViewById<Button>(R.id.goToCamera)
//
//
//        val listener = View.OnClickListener(){
//            when(it.id){
//                R.id.goToCamera -> {
//                    getContent.launch("image/*")
//                }
//            }
//        }
//
//        goToCamera.setOnClickListener(listener)
//    }
//
//}