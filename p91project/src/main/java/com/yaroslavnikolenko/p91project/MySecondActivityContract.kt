package com.yaroslavnikolenko.p91project

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MySecondActivityContract: ActivityResultContract<String, String?>(){
    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, NameActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when{
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getStringExtra("nameText")
    }

}