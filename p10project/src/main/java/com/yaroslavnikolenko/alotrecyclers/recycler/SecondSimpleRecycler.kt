package com.yaroslavnikolenko.alotrecyclers.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.alotrecyclers.R
import kotlin.random.Random

class SecondSimpleRecycler(val context: Context): RecyclerView.Adapter<SecondSimpleRecycler.MyViewHolder>(){

    val arrayOfStr = context.resources.getStringArray(R.array.stringArray)
    val img = listOf(R.drawable.first_icon, R.drawable.second_icon, R.drawable.third_icon, R.drawable.fourt_icon)

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var secondRecyclerView: TextView? = null
        var secondRecyclerImg1: ImageView? = null
        var secondRecyclerImg2: ImageView? = null

        init {
            secondRecyclerView = itemView.findViewById(R.id.secondRecycler)
            secondRecyclerImg1 = itemView.findViewById(R.id.imageView1)
            secondRecyclerImg2 = itemView.findViewById(R.id.imageView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return SecondSimpleRecycler.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_simple_second, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.secondRecyclerView!!.text = (arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString() + " " +
                arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString() + " " +
                arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString())
        holder.secondRecyclerImg1!!.background = context.getDrawable(img[Random.nextInt(0,3)])
        holder.secondRecyclerImg2!!.background = context.getDrawable(img[Random.nextInt(0,3)])
    }

    override fun getItemCount(): Int {
        return  arrayOfStr.size * 10
    }
}