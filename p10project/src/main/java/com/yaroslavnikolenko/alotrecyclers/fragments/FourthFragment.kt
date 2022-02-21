package com.yaroslavnikolenko.alotrecyclers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaroslavnikolenko.alotrecyclers.R

class FourthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflater1 = inflater.inflate(R.layout.fragment_fourth, container, false)

        return inflater1
    }

}