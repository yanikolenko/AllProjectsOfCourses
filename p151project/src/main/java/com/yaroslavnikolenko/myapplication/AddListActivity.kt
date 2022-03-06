package com.yaroslavnikolenko.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yaroslavnikolenko.myapplication.databinding.ActivityAddListBinding
import com.yaroslavnikolenko.myapplication.dialog.dialogAddTask
import com.yaroslavnikolenko.myapplication.realtimedatabase.data.ToDoListData
import com.yaroslavnikolenko.myapplication.realtimedatabase.viewmodel.ToDoListViewModel
import com.yaroslavnikolenko.myapplication.recyclers.AddListRecyclerAdapter
import java.text.SimpleDateFormat
import java.util.*

class AddListActivity : AppCompatActivity() {

    private var activityAddListBinding: ActivityAddListBinding? = null
    private var addListRecyclerAdapter: AddListRecyclerAdapter? = null
    private var toDoListViewModel: ToDoListViewModel? = null
    private var positionStringExtra: String? = null

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
        menuItemClickListener()

    }

    private fun navigateToMainAct(){
        activityAddListBinding?.materialToolbar?.setNavigationOnClickListener {
            initIntentToMA()
        }
    }

    private fun initIntentToMA(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun addTask(){
        activityAddListBinding?.addTaskFloatingButton?.setOnClickListener {
            dialogAddTask(this,R.layout.custom_dialog, R.id.positiveBtn,
                R.id.negativeBtn, R.id.editTextDialog, toDoListViewModel!!)
            activityAddListBinding?.addTasksEditView?.clearFocus()
        }
    }

    private fun observeTaskList(){
        toDoListViewModel?._listTasks?.observe(this){
            addListRecyclerAdapter?.setDataListOfTasks(it)
            activityAddListBinding?.addListProgressBar?.visibility = View.INVISIBLE
        }
    }

    private fun initAdapter(){
        addListRecyclerAdapter = AddListRecyclerAdapter{value, id ->
            toDoListViewModel?._listTasks?.value!![id] = listOf(toDoListViewModel?._listTasks?.value!![id][0], changeLogicValue(value.toString()))
        }
        activityAddListBinding?.addTasksRecyclerView?.adapter = addListRecyclerAdapter
    }

    private fun initViewModel(){
        toDoListViewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
    }

    private fun menuItemClickListener(){
        activityAddListBinding?.materialToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.confirm_toolbar -> {
                    if (toDoListViewModel?._listTasks?.value?.size != 0){
                        toDoListViewModel?.addDataToDB(false, activityAddListBinding?.addTasksEditView?.text.toString(), setDate())
                        initIntentToMA()
                    }else{
                        Toast.makeText(this, "Потрібно додати завдання !", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.change_toolbar -> {
                    try {
                        toDoListViewModel?.updateDataInDb(positionStringExtra!!, false, activityAddListBinding?.addTasksEditView?.text.toString(), setDate())
                        initIntentToMA()
                    }catch (e: NullPointerException){}
                    true
                }
                R.id.delete_toolbar -> {
                   try {
                       toDoListViewModel?.deleteDataOfDB(positionStringExtra!!)
                       initIntentToMA()
                   }catch (e: NullPointerException){}

                    true
                }
                else -> true
            }
        }
    }

    private fun getIntExtra(){

        if (intent.getStringExtra("IdOfChildFirebase") != null){

            positionStringExtra = intent.getStringExtra("IdOfChildFirebase")

            changeVisibilityToolbarMenu(false, true)

            toDoListViewModel?.getDataFromDBById(positionStringExtra!!)?.observe(this, {
                setNameOfList(it)
                it.tasks?.forEach {
                    toDoListViewModel?._listTasks?.value?.add(it)
                }
                addListRecyclerAdapter?.setDataListOfTasks(toDoListViewModel?._listTasks?.value!!)
            })
        }
    }

    private fun setNameOfList(toDoData: ToDoListData){
        activityAddListBinding?.addTasksEditView?.setText(toDoData.nameOfList)
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

    fun changeLogicValue(value: String): String{
        return when(value){
            "1" -> "0"
            "0" -> "1"
            else -> "0"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityAddListBinding = null
    }
}