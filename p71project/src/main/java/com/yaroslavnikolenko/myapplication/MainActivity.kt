package com.yaroslavnikolenko.myapplication

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    var radioAnimals: RadioButton? = null
    var radioGroup: RadioGroup? = null
    private var flag: Boolean = true
    var radioPlants: RadioButton? = null
    var nameOfBeing: TextView? = null
    var describeOfBeing: TextView? = null
    var generalImg: ImageView? = null

    var myRes: String? = null

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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        setNewValues(R.drawable.sparrow, resources.getString(R.string.sparrowName), resources.getString(R.string.sparrowDescribe))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.sparrow -> setNewValues(R.drawable.sparrow, resources.getString(R.string.sparrowName), resources.getString(R.string.sparrowDescribe))
//            R.id.crow -> setNewValues(R.drawable.crow, resources.getString(R.string.crowName), resources.getString(R.string.crowDescribe))
//            R.id.owl -> setNewValues(R.drawable.owl, resources.getString(R.string.owlName), resources.getString(R.string.owlDescribe))
//            R.id.retriever -> setNewValues(R.drawable.retriever, resources.getString(R.string.retrieverName), resources.getString(R.string.retrieverDescribe))
//            R.id.cat -> setNewValues(R.drawable.cat, resources.getString(R.string.catName), resources.getString(R.string.catDescribe))
//            R.id.hedgehog -> setNewValues(R.drawable.hedgehog, resources.getString(R.string.hedgehogName), resources.getString(R.string.hedgehogDescribe))
//            R.id.snake -> setNewValues(R.drawable.snake, resources.getString(R.string.snakeName), resources.getString(R.string.snakeDescribe))
//            R.id.iguana -> setNewValues(R.drawable.iguana, resources.getString(R.string.iguanaName), resources.getString(R.string.iguanaDescribe))
//            R.id.frog -> setNewValues(R.drawable.frog, resources.getString(R.string.frogName), resources.getString(R.string.frogDescribe))
//
//            R.id.xlorela -> setNewValues(R.drawable.xlorela, resources.getString(R.string.xlorelaName), resources.getString(R.string.xlorelaDescribe))
//            R.id.voltriks -> setNewValues(R.drawable.voltriks, resources.getString(R.string.voltriksName), resources.getString(R.string.voltriksDescribe))
//            R.id.volvoks -> setNewValues(R.drawable.volvoks, resources.getString(R.string.volvoksName), resources.getString(R.string.volvoksDescribe))
//            R.id.brium -> setNewValues(R.drawable.brium, resources.getString(R.string.briumName), resources.getString(R.string.briumDescribe))
//            R.id.dikran -> setNewValues(R.drawable.dikran, resources.getString(R.string.dikranName), resources.getString(R.string.dikranDescribe))
//            R.id.hylocomium -> setNewValues(R.drawable.hylocomium, resources.getString(R.string.hylocomiumName), resources.getString(R.string.hylocomiumDescribe))
//            R.id.yalitsa -> setNewValues(R.drawable.yalitsa, resources.getString(R.string.yalitsaName), resources.getString(R.string.yalitsaDescribe))
//            R.id.modrina -> setNewValues(R.drawable.modrina, resources.getString(R.string.modrinaName), resources.getString(R.string.modrinaDescribe))
//            R.id.sosna -> setNewValues(R.drawable.sosna, resources.getString(R.string.sosnaName), resources.getString(R.string.sosnaDescribe))
//        }
//
        myRes = resources.getResourceEntryName(item.itemId)

        try {
            val value = getString(resources.getIdentifier(resources.getResourceEntryName(item.itemId).plus("Describe"), "string", packageName))
            setNewValues(resources.getIdentifier(myRes, "drawable", packageName), item.toString(), value)
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
                    flag = true
                    setNewValues(R.drawable.sparrow, resources.getString(R.string.sparrowName), resources.getString(R.string.sparrowDescribe))
                }
                R.id.plant -> {
                    flag = false
                    setNewValues(R.drawable.sosna, resources.getString(R.string.sosnaName), resources.getString(R.string.sosnaDescribe))
                }
            }
        }
        return flag
    }

    private fun setNewValues(image: Int, name: String, describe: String ){
        if (image != 0){
            generalImg!!.setImageResource(image)
            nameOfBeing!!.text = name
            describeOfBeing!!.text = describe
        }
    }

}