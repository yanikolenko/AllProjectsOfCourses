package com.yaroslavnikolenko.alotrecyclers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yaroslavnikolenko.alotrecyclers.R
import com.yaroslavnikolenko.alotrecyclers.data.ChatViewModel
import com.yaroslavnikolenko.alotrecyclers.recycler.CustomRecyclerView

class FifthFragment() : Fragment(){

    var fromFiveToDatas: FloatingActionButton? = null
    var chatViewModel: ChatViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_fifth, container, false)

        fromFiveToDatas = inflater.findViewById(R.id.fromFiveToDatas)
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        val adapter = CustomRecyclerView()
        val recyclerView: RecyclerView = inflater.findViewById(R.id.fifthRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        chatViewModel!!.readAllData.observe(viewLifecycleOwner, Observer { chat ->
            adapter.setData(chat)
        })

        fromFiveToDatas!!.setOnClickListener {
            val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_fifthFragment_to_addSomeInfo)
        }

        return inflater
    }





}