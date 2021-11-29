package com.yaroslavnikolenko.alotrecyclers.recycler

import android.content.Context
import android.icu.util.TimeUnit.values
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.alotrecyclers.MainActivity
import com.yaroslavnikolenko.alotrecyclers.R
import kotlin.random.Random

class SimpleRecycler(val context: Context): RecyclerView.Adapter<SimpleRecycler.MyViewHolder>() {

    val arrayOfStr = context.resources.getStringArray(R.array.stringArray)

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var firstRecycler: TextView? = null

        init {
            firstRecycler = itemView.findViewById(R.id.firstRecycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return SimpleRecycler.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_simple, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.firstRecycler!!.text = (arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString() + " " +
                arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString() + " " +
                arrayOfStr[Random.nextInt(0, arrayOfStr.size - 1)].toString())
    }

    override fun getItemCount(): Int {
        return arrayOfStr.size * 10
    }
}