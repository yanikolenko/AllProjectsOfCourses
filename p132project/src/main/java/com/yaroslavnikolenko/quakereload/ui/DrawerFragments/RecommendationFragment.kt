package com.yaroslavnikolenko.quakereload.ui.DrawerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaroslavnikolenko.quakereload.ui.Recyclers.RecommendationRecycler
import com.yaroslavnikolenko.quakereload.databinding.FragmentRecommendationBinding

class RecommendationFragment : Fragment() {

    var recommendationBinding: FragmentRecommendationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        recommendationBinding = FragmentRecommendationBinding.inflate(layoutInflater, container, false)

        return recommendationBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

    }

    private fun initRecycler() { recommendationBinding?.recommendationRecycler?.adapter = RecommendationRecycler(requireContext()) }

    override fun onDestroyView() {
        super.onDestroyView()
        recommendationBinding = null
    }

}