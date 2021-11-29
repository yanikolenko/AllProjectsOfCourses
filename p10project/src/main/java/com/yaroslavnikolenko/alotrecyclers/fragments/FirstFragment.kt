package com.yaroslavnikolenko.alotrecyclers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.alotrecyclers.MainActivity
import com.yaroslavnikolenko.alotrecyclers.R
import com.yaroslavnikolenko.alotrecyclers.recycler.SimpleRecycler

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_first, container, false)

        val adapter = SimpleRecycler(requireContext())
        val recyclerView: RecyclerView = inflater.findViewById(R.id.firstRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        return inflater
    }

}