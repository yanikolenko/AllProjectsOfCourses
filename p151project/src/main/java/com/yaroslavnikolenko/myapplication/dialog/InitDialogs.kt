package com.yaroslavnikolenko.myapplication.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yaroslavnikolenko.myapplication.context.App
import com.yaroslavnikolenko.myapplication.realtimedatabase.viewmodel.ToDoListViewModel

fun dialogAddTask(context: Context, layout: Int, positiveBtn: Int, negativeBtn: Int, editField: Int, toDoListViewModel: ToDoListViewModel){
    val mDialogView = LayoutInflater.from(context).inflate(layout, null)

    val mBuilder = AlertDialog.Builder(context)
        .setView(mDialogView)
        .setCancelable(false)
    val mAlertDialog = mBuilder.show()

    mAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    mDialogView.findViewById<TextView>(positiveBtn).setOnClickListener {
        if (mDialogView.findViewById<EditText>(editField).text.toString() != ""){
            toDoListViewModel._listTasks.value?.add(listOf(mDialogView.findViewById<EditText>(editField).text.toString(), "0"))

        }else{
            Toast.makeText(App.context, "Поле не може бути пустим !", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.hide()
    }

    mDialogView.findViewById<TextView>(negativeBtn).setOnClickListener {
        mAlertDialog.hide()
    }
}




