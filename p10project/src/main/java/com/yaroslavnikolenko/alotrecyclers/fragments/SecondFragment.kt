package com.yaroslavnikolenko.alotrecyclers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.alotrecyclers.R
import com.yaroslavnikolenko.alotrecyclers.recycler.SecondSimpleRecycler

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var inflater = inflater.inflate(R.layout.fragment_second, container, false)

        val adapter = SecondSimpleRecycler(requireContext())
        val recycler = inflater.findViewById<RecyclerView>(R.id.secondRecyclerView)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        return inflater
    }

}