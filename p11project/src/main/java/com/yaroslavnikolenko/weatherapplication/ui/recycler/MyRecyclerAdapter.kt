package com.yaroslavnikolenko.weatherapplication.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.weatherapplication.R
import com.yaroslavnikolenko.weatherapplication.ui.HourItem
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(val context: Context, val array1: List<HourItem?>): RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var temperature: TextView
        lateinit var time: TextView
        lateinit var imgWeather: ImageView

        init {
            temperature = itemView.findViewById(R.id.temperatureView)
            time = itemView.findViewById(R.id.timeView)
            imgWeather = itemView.findViewById(R.id.imageWeatherView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.temperature.text = "${array1[position]?.tempC.toString()} °C"
        holder.time.text = array1[position]?.time

        Picasso.with(context).load("https:${array1[position]?.condition?.icon}").into(holder.imgWeather);

    }

    override fun getItemCount(): Int {
        return array1.size
    }


}

