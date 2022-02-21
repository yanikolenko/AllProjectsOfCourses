package com.yaroslavnikolenko.alotrecyclers.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.alotrecyclers.R
import com.yaroslavnikolenko.alotrecyclers.data.Chat

class CustomRecyclerView(): RecyclerView.Adapter<CustomRecyclerView.MyViewHolder>() {

    var chatList = emptyList<Chat>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var mainImg: ImageView? = null
        var name1: TextView? = null
        var massage1: TextView? = null

        init {
            mainImg = itemView.findViewById(R.id.fifthImg)
            name1 = itemView.findViewById(R.id.fifthName)
            massage1 = itemView.findViewById(R.id.fifthMassage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = chatList[position]
        holder.massage1!!.text = currentItem.name
        holder.name1?.text = currentItem.massage
        holder.mainImg?.setImageBitmap(currentItem.img)
    }

    override fun getItemCount(): Int {
        return  chatList.size
    }

    fun setData(chat: List<Chat>){
        this.chatList = chat
        notifyDataSetChanged()
    }
}