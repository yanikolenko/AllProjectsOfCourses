package com.yaroslavnikolenko.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yaroslavnikolenko.myapplication.Converter.ConverterTaskList
import com.yaroslavnikolenko.myapplication.Dialog.InitDialogs
import com.yaroslavnikolenko.myapplication.MainViewModel.MainViewModel
import com.yaroslavnikolenko.myapplication.databinding.ActivityAddListBinding
import com.yaroslavnikolenko.myapplication.recyclers.AddListRecyclerAdapter
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class AddListActivity : AppCompatActivity() {

    private var activityAddListBinding: ActivityAddListBinding? = null
    private var addListRecyclerAdapter: AddListRecyclerAdapter? = null
    private var mainViewModel: MainViewModel? = null
    private var positionIntExtra: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddListBinding = ActivityAddListBinding.inflate(layoutInflater)
        setContentView(activityAddListBinding?.root)

        initUI()

        initViewModel()

        changeVisibilityToolbarMenu(true, false)

        getIntExtra()

        initAdapter()

        navigateToMainAct()

        addTask()

        observeTaskList()

        observeTaskStatus()

        menuItemClickListener()

    }

    private fun navigateToMainAct(){
        activityAddListBinding?.materialToolbar?.setNavigationOnClickListener {
            initIntentToMA()
        }
    }

    private fun initIntentToMA(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun addTask(){
        activityAddListBinding?.addTaskFloatingButton?.setOnClickListener {
            InitDialogs.dialogAddTask(this,R.layout.custom_dialog, R.id.positiveBtn, R.id.negativeBtn, R.id.editTextDialog, mainViewModel!!)
            activityAddListBinding?.addTasksEditView?.clearFocus()
        }
    }

    private fun observeTaskList(){
        mainViewModel?.infoToRecycler?.observe(this){
            addListRecyclerAdapter?.setDataListOfTasks(it)
        }
    }

    private fun observeTaskStatus(){
        mainViewModel?.taskStatusToRecycler?.observe(this){
            addListRecyclerAdapter?.setDataStatusOfTasks(it)
        }
    }

    private fun initAdapter(){
        addListRecyclerAdapter = AddListRecyclerAdapter{value, id ->
            mainViewModel!!.taskStatus[id] = value.toString()
        }
        activityAddListBinding?.addTasksRecyclerView?.adapter = addListRecyclerAdapter
    }

    private fun initViewModel(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun menuItemClickListener(){
        activityAddListBinding?.materialToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.confirm_toolbar -> {

                    if (mainViewModel?.infoToRecycler?.value?.size != 0){
                        mainViewModel?.addNewList(0,activityAddListBinding?.addTasksEditView?.text.toString(),
                            setDate())
                        initIntentToMA()
                    }else{
                        Toast.makeText(this, "Потрібно додати завдання !", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.change_toolbar -> {
                    try {
                        mainViewModel?.changeList((positionIntExtra!!), activityAddListBinding?.addTasksEditView?.text.toString(),
                            setDate())
                        //observeViewModel()
                        initIntentToMA()
                    }catch (e: NullPointerException){}
                    true
                }
                R.id.delete_toolbar -> {
                   try {
                       InitDialogs.deleteList(this, R.layout.delete_list_dialog, R.id.positiveBtnDelete, R.id.negativeBtnDelete, ::observeTaskList, ::initIntentToMA, mainViewModel!!, positionIntExtra!!)
                   }catch (e: NullPointerException){}

                    true
                }
                else -> true
            }
        }
    }

    private fun getIntExtra(){

        if (intent.getIntExtra("position", -1) != -1){

            changeVisibilityToolbarMenu(false, true)

            positionIntExtra = intent.getIntExtra("position", 0)
            ConverterTaskList.toTaskList(mainViewModel?.getListForId(positionIntExtra!!)!!.taskArray).forEach { it ->
                it.forEach {
                    mainViewModel?.mylist?.add(it)
                }
            }

            ConverterTaskList.toCompleteList(mainViewModel?.getListForId(positionIntExtra!!)!!.complete).forEach {
                mainViewModel?.taskStatus?.add(it)
            }

            setNameOfList(positionIntExtra!!)

        }
    }

    private fun setNameOfList(id: Int){
        activityAddListBinding?.addTasksEditView?.setText(mainViewModel?.getListForId(id)!!.nameOfList)
    }

    private fun initUI(){
        initViewModel()

    }

    private fun changeVisibilityToolbarMenu(confirm: Boolean, change: Boolean){
        activityAddListBinding?.materialToolbar?.menu?.findItem(R.id.confirm_toolbar)?.isVisible = confirm
        activityAddListBinding?.materialToolbar?.menu?.findItem(R.id.change_toolbar)?.isVisible = change
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate(): String{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEE,d MMM HH:mm:ss")
        return formatter.format(date)
    }
}