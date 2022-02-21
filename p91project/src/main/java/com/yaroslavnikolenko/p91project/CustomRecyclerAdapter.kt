package com.yaroslavnikolenko.p91project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(val mContext: Context, private val names: List<String>, private val flags: List<Int>):RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameOfCountry: TextView? = null
        var flagOfCountry: ImageView? = null

        init {
            nameOfCountry = itemView.findViewById(R.id.nameOfCountry)
            flagOfCountry = itemView.findViewById(R.id.flagOfCountry)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.element_of_recycler, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameOfCountry?.text = names[position]
        holder.flagOfCountry?.setImageResource(flags[position])

        holder.itemView.setOnClickListener {
            val activity = mContext as CountryActivity
            activity.setResult(Activity.RESULT_OK, Intent().putExtra("nameText1", InfoAboutCountry(names[position], flags[position])))
            activity.finish()

        }


    }

    override fun getItemCount(): Int {
        return names.size
    }
}