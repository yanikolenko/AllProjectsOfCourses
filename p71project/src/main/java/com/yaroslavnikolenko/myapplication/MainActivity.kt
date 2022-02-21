package com.yaroslavnikolenko.myapplication

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    var radioAnimals: RadioButton? = null
    var radioGroup: RadioGroup? = null
    var radioPlants: RadioButton? = null
    var nameOfBeing: TextView? = null
    var describeOfBeing: TextView? = null
    var generalImg: ImageView? = null

    var myRes: String? = null

    var vm: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_main)

        radioAnimals = findViewById(R.id.animal)
        radioGroup = findViewById(R.id.radioGroup)
        radioPlants = findViewById(R.id.plant)
        nameOfBeing = findViewById(R.id.nameOfBeing)
        describeOfBeing = findViewById(R.id.describeOfBeing)
        generalImg = findViewById(R.id.generalPhoto)

        ////////////////////////////////////////////////////////////////////////////////
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        ////////////////////////////////////////////////////////////////////////////////

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        try {
            setNewValues(vm!!.liveDataImg.value!!, vm!!.liveDataTitle.value!!, vm!!.liveDataSubTitle.value!!)
        }catch (e: NullPointerException){
            setNewValues(R.drawable.sparrow, resources.getString(R.string.sparrowName), resources.getString(R.string.sparrowDescribe))
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        myRes = resources.getResourceEntryName(item.itemId)
        vm!!.liveDataImg.value = resources.getIdentifier(myRes, "drawable", packageName)
        vm!!.liveDataTitle.value = item.toString()

        try {
            val value = getString(resources.getIdentifier(resources.getResourceEntryName(item.itemId).plus("Describe"), "string", packageName))
            vm!!.liveDataSubTitle.value = value
            setNewValues(vm!!.liveDataImg.value!!, vm!!.liveDataTitle.value!!, vm!!.liveDataSubTitle.value!!)
        }catch (e: Resources.NotFoundException){

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (onCheckedRadio()){
            menu!!.setGroupVisible(R.id.groupOfAnimals, true)
            menu.setGroupVisible(R.id.groupOfPlants, false)
        }else if (!onCheckedRadio()){
            menu!!.setGroupVisible(R.id.groupOfAnimals, false)
            menu.setGroupVisible(R.id.groupOfPlants, true)
        }
        return super.onPrepareOptionsMenu(menu)

    }

    private fun onCheckedRadio():Boolean{

        radioGroup!!.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId){
                R.id.animal -> {
                    vm!!.liveDataBoolean.value = true
                    setNewValues(R.drawable.sparrow, resources.getString(R.string.sparrowName), resources.getString(R.string.sparrowDescribe))
                }
                R.id.plant -> {
                    vm!!.liveDataBoolean.value = false
                    setNewValues(R.drawable.sosna, resources.getString(R.string.sosnaName), resources.getString(R.string.sosnaDescribe))
                }
            }
        }
        return vm!!.liveDataBoolean.value!!
    }

    private fun setNewValues(image: Int, name: String, describe: String ){
        if (image != 0){
            generalImg!!.setImageResource(image)
            nameOfBeing!!.text = name
            describeOfBeing!!.text = describe
        }
    }

}