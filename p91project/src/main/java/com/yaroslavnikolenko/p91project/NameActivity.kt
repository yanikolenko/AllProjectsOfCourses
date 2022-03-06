package com.yaroslavnikolenko.p91project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class NameActivity : AppCompatActivity() {

    var nameInfo: EditText? = null
    var  arrowInTool: ImageView? = null
    var saveName: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        nameInfo = findViewById(R.id.editPersonName)
        arrowInTool = findViewById(R.id.arrowInTool)
        saveName = findViewById(R.id.saveName)

        val listener = View.OnClickListener(){
            when(it.id){
                R.id.arrowInTool -> callNewAct(false)
                R.id.saveName -> callNewAct(true)
            }
        }

        arrowInTool!!.setOnClickListener(listener)
        saveName!!.setOnClickListener(listener)

    }

    private fun callNewAct(flag: Boolean){
        if (flag){
            setResult(Activity.RESULT_OK, Intent().putExtra("nameText", nameInfo?.text.toString()))
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}