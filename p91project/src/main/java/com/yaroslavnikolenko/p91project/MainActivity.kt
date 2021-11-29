package com.yaroslavnikolenko.p91project

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(){

    private var profileImage: ImageView? = null
    private var chooseNewImg: FloatingActionButton? = null
    private var toActName: LinearLayout? = null
    private var toActCount: LinearLayout? = null
    private var textViewCountry: TextView? = null
    private var flagCount: ImageView? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        profileImage!!.setImageURI(uri)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleCameraImage(result.data)
            }
        }

    private val nameActivity = NameActivity()
    private val countryActivity = CountryActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toActName = findViewById(R.id.toActName)
        profileImage = findViewById(R.id.profile_image)
        chooseNewImg = findViewById(R.id.choose_new_photo)
        toActCount = findViewById(R.id.toActCount)
        textViewCountry = findViewById(R.id.textViewCountry)
        flagCount = findViewById(R.id.flagCountry)

        initIntent("nameText", nameActivity, toActName!!, true)
        initIntent("nameText1", countryActivity, toActCount!!, false)

        listenerNewPhoto()


    }

    private fun initIntent(name: String, activity: Activity, view: View, bool: Boolean){

        val getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (bool){
                        val value = it.data?.getStringExtra(name)
                        findViewById<TextView>(R.id.textViewName).text = value
                    }else{
                        val documentInfo: InfoAboutCountry? = it.data?.getParcelableExtra<Parcelable>("nameText1") as InfoAboutCountry?
                        textViewCountry!!.text = documentInfo!!.name
                        flagCount!!.setImageResource(documentInfo.flag)
                    }
                }
            }

        val intent = Intent(this, activity::class.java)

        view.setOnClickListener {
            getResult.launch(intent)
        }

    }

    private fun showPopUp(view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.custom_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.toCamera -> {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    resultLauncher.launch(cameraIntent)
                } R.id.toGallery -> {
                    getContent.launch("image/*")
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        profileImage!!.setImageBitmap(bitmap)

    }

    private fun listenerNewPhoto(){
        val listener = View.OnClickListener {
            when (it.id){
                R.id.profile_image -> showPopUp(chooseNewImg!!)
                R.id.choose_new_photo -> showPopUp(chooseNewImg!!)
            }
        }

        profileImage!!.setOnClickListener(listener)
        chooseNewImg!!.setOnClickListener(listener)
    }



}



