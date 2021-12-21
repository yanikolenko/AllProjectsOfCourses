package com.yaroslavnikolenko.myapplication.ui.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.R
import com.yaroslavnikolenko.myapplication.databinding.FragmentRecomendationBinding
import com.yaroslavnikolenko.myapplication.recycler.RecommendationAdapter

class RecomendationFragment : Fragment() {

    private var _binding: FragmentRecomendationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecomendationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerWithRecommendation)
        recycler.adapter = RecommendationAdapter(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}