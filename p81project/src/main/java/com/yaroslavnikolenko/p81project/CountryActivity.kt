package com.yaroslavnikolenko.p81project

import InfoAboutCountry
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountryActivity : AppCompatActivity() {

    private var nameOfCount:Array<String>? = null
    private var flagOfCount:Array<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        nameOfCount = resources.getStringArray(R.array.nameOfCount)
        flagOfCount = arrayOf(R.drawable.afghanistan, R.drawable.albania, R.drawable.algeria, R.drawable.andorra)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(this, fillListOfName(), fillListOfFlags())

    }

    private fun fillListOfName(): List<String> {
        val data = mutableListOf<String>()
        for (i in  nameOfCount!!){
            data.add(i)
        }
        return data
    }

    private fun fillListOfFlags(): List<Int>{
        val data = mutableListOf<Int>()
        for (i in flagOfCount!!){
            data.add(i)
        }
        return  data
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}