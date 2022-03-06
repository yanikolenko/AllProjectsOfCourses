package com.yaroslavnikolenko.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.yaroslavnikolenko.myapplication.MainViewModel.MainViewModel
import com.yaroslavnikolenko.myapplication.databinding.ActivityMainBinding
import com.yaroslavnikolenko.myapplication.recyclers.MainRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding?.root)

        initViewModel()

        navigateToAddListAct()

        initRecycler()

        showHideEmptyListImg()
    }

    private fun navigateToAddListAct(){
        activityMainBinding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecycler(){
        mainRecyclerAdapter = MainRecyclerAdapter{
            openCheckedList(it.id)
        }
        activityMainBinding?.mainRecyclerView?.adapter = mainRecyclerAdapter
    }

    private fun initViewModel(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun showHideEmptyListImg(){
        if(mainViewModel?.getAllLists()!!.isNotEmpty()){
            activityMainBinding?.listEmptyImg?.isVisible = false
            activityMainBinding?.listEmptyTxt?.isVisible = false
        }else{
            activityMainBinding?.listEmptyImg?.isVisible = true
            activityMainBinding?.listEmptyTxt?.isVisible = true
        }
    }

    private fun openCheckedList(id: Int){
        val intent = Intent(this, AddListActivity::class.java)
        intent.putExtra("position", id)
        MainViewModel.numberOfCheckedList.value = id
        startActivity(intent)
    }
}