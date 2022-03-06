package com.yaroslavnikolenko.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.yaroslavnikolenko.myapplication.databinding.ActivityMainBinding
import com.yaroslavnikolenko.myapplication.realtimedatabase.data.ToDoListData
import com.yaroslavnikolenko.myapplication.realtimedatabase.viewmodel.ToDoListViewModel
import com.yaroslavnikolenko.myapplication.recyclers.MainRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null
    private var toDoListViewModel: ToDoListViewModel? = null
    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding?.root)

        initViewModel()
        navigateToAddListAct()
        initRecycler()

    }

    private fun navigateToAddListAct(){
        activityMainBinding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecycler(){
        mainRecyclerAdapter = MainRecyclerAdapter{ toDoObject, id ->
            openCheckedList(toDoObject.objectId.toString(), id)
        }
    }

    private fun initViewModel(){
        toDoListViewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
        toDoListViewModel?.getDataFromDB()?.observe(this, {
            showHideEmptyListImg(it)
            activityMainBinding?.mainProgressBar?.visibility = View.INVISIBLE
            activityMainBinding?.mainRecyclerView?.adapter = mainRecyclerAdapter
            mainRecyclerAdapter?.setToDoList(it)

        })
    }

    private fun showHideEmptyListImg(value: MutableList<ToDoListData>){
        if(value.isNotEmpty() && flag){
            setVisibility(false)
            flag = false
        }else if (flag){
            setVisibility(true)
            flag = false
        }
    }

    private fun openCheckedList(idChild: String, id: Int){
        val intent = Intent(this, AddListActivity::class.java)
        intent.putExtra("IdOfChildFirebase", idChild)
        intent.putExtra("ID", id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun setVisibility(isVisible: Boolean){
        if (isVisible){
            activityMainBinding?.listEmptyImg?.isVisible = true
            activityMainBinding?.listEmptyTxt?.isVisible = true
        }else{
            activityMainBinding?.listEmptyImg?.isVisible = false
            activityMainBinding?.listEmptyTxt?.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}