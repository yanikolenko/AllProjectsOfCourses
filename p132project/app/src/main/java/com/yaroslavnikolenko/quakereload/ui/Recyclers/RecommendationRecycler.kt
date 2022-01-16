package com.yaroslavnikolenko.quakereload.ui.Recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.quakereload.R
import com.yaroslavnikolenko.quakereload.databinding.RecommendationRecyclerItemBinding

class RecommendationRecycler(private val context: Context): RecyclerView.Adapter<RecommendationRecycler.ViewHolder>() {

    private val recommendationArray = context.resources.getStringArray(R.array.array_recommendation)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = RecommendationRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.recommendationRecyclerItemBinding.recommendationText.text = recommendationArray[position]
    }

    override fun getItemCount(): Int = recommendationArray.size

    class ViewHolder(var recommendationRecyclerItemBinding: RecommendationRecyclerItemBinding): RecyclerView.ViewHolder(recommendationRecyclerItemBinding.root)

}
