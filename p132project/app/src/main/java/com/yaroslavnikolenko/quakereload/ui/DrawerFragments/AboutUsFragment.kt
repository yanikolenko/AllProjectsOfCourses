package com.yaroslavnikolenko.quakereload.ui.DrawerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaroslavnikolenko.quakereload.databinding.FragmentAboutUsBinding


class AboutUsFragment : Fragment() {

    private var fragmentAboutUsBinding: FragmentAboutUsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAboutUsBinding = FragmentAboutUsBinding.inflate(layoutInflater, container, false)
        return fragmentAboutUsBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAboutUsBinding = null
    }


}