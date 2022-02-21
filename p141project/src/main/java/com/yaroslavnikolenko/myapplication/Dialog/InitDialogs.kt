package com.yaroslavnikolenko.myapplication.Dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yaroslavnikolenko.myapplication.GlobalContext.App
import com.yaroslavnikolenko.myapplication.MainViewModel.MainViewModel

class InitDialogs {

    companion object{

        fun dialogAddTask(context: Context, layout: Int, positiveBtn: Int, negativeBtn: Int, editField: Int, viewModel: MainViewModel){
            val mDialogView = LayoutInflater.from(context).inflate(layout, null)

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setCancelable(false)
            val mAlertDialog = mBuilder.show()

            mAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            mDialogView.findViewById<TextView>(positiveBtn).setOnClickListener {
                if (mDialogView.findViewById<EditText>(editField).text.toString() != ""){
                    viewModel.mylist.add(mDialogView.findViewById<EditText>(editField).text.toString())

                    viewModel.taskStatus.add("0")

                }else{
                    Toast.makeText(App.context, "Поле не може бути пустим !", Toast.LENGTH_SHORT).show()
                }
                mAlertDialog.hide()
            }

            mDialogView.findViewById<TextView>(negativeBtn).setOnClickListener {
                mAlertDialog.hide()
            }
        }

        fun deleteList(context: Context, layout: Int, positiveBtn: Int, negativeBtn: Int, observe: () -> Unit, intent: () -> Unit, viewModel: MainViewModel, position: Int){
            val mDialogView = LayoutInflater.from(context).inflate(layout, null)

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setCancelable(false)
            val mAlertDialog = mBuilder.show()

            mAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            mDialogView.findViewById<TextView>(positiveBtn).setOnClickListener {
                intent()
                viewModel.deleteById(position)
            }

            mDialogView.findViewById<TextView>(negativeBtn).setOnClickListener {
                mAlertDialog.hide()
            }
        }


    }

}