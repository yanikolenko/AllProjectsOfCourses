package com.yaroslavnikolenko.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.R

class RecommendationAdapter(val context: Context): RecyclerView.Adapter<RecommendationAdapter.MyViewHolder>() {

    var stringsRecommendation = context.resources.getStringArray(R.array.string_recommendation)

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var recyclerRecommendation: TextView? = null

        init {
            recyclerRecommendation = itemView.findViewById(R.id.recyclerRecomView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_recommendation_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerRecommendation?.text = stringsRecommendation[position]

    }

    override fun getItemCount(): Int {
        return stringsRecommendation.size
    }

}