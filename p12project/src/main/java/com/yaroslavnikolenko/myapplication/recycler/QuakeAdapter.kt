package com.yaroslavnikolenko.myapplication.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.*
import com.yaroslavnikolenko.myapplication.additionalClasses.ChooseText
import com.yaroslavnikolenko.myapplication.additionalClasses.CountDaysAgo
import com.yaroslavnikolenko.myapplication.constants.QuakeApiConsts
import java.text.SimpleDateFormat

class QuakeAdapter(val context: Context, val itemClickListener: (ArrayList<String?>) -> Unit) : RecyclerView.Adapter<QuakeAdapter.MyViewHolder>() {

    var data: List<FeaturesItem?> = emptyList()

    fun dataItem(newData: List<FeaturesItem?>){
        data = newData
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var recyclerDate: TextView? = null
        var recyclerSide: TextView? = null
        var recyclerSensitive: TextView? = null
        var recyclerSizeQuake: TextView? = null
        var magnitude: Double? = null
        var locality: String? = null
        var magnitudeFormated: String? = null
        var date: String? = null
        var formattedDay: Int? = null
        var formattedMonth: Int? = null
        val sdf = SimpleDateFormat(QuakeApiConsts.TIME_STANDART)
        val day = SimpleDateFormat("dd")
        val month = SimpleDateFormat("MM")

        init {
            recyclerDate = itemView.findViewById(R.id.recyclerDateView)
            recyclerSide = itemView.findViewById(R.id.recyclerSideView)
            recyclerSensitive = itemView.findViewById(R.id.recyclerSensitiveView)
            recyclerSizeQuake = itemView.findViewById(R.id.recyclerSizeQuakeView)

        }

        fun bind(featuresItem: FeaturesItem?) {

            magnitude = featuresItem?.properties?.magnitude
            magnitudeFormated = String.format("%.1f", magnitude)
            locality = featuresItem?.properties?.locality
            date = featuresItem?.properties?.time

            workWithDate(featuresItem)
            initUI()
            ChooseText(itemView.context).chooseText(magnitude!!, recyclerSensitive!!)
        }

        fun initUI(){
            recyclerSide?.text = locality
            recyclerSizeQuake?.text = String.format("%.1f", magnitude)
            recyclerDate?.text = when(returnDiffOfDays(formattedMonth!!, formattedDay!!)){
                "Today" -> returnDiffOfDays(formattedMonth!!, formattedDay!!)
                else -> "${returnDiffOfDays(formattedMonth!!, formattedDay!!)} days ago"
            }
        }

        fun returnDiffOfDays(month: Int, day: Int): String = CountDaysAgo(month, day).diff()

        private fun workWithDate(featuresItem: FeaturesItem?){
            val date = featuresItem?.properties?.time
            val formatDay = sdf.parse(date)
            val formatMonth = sdf.parse(date)
            formattedDay = day.format(formatDay).toInt()
            formattedMonth = month.format(formatMonth).toInt()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(data[position])

        //Нижній блок не зміг перенести у верхній клас, бо сварився постійно itemClickListener, а інакше як в
        //параметрі класу я не знаю де можна ініціювати
        holder.itemView.setOnClickListener {
            val listWithData = arrayListOf(holder.magnitudeFormated, holder.locality.toString(), holder.date.toString())
            itemClickListener(listWithData)
        }

    }

    override fun getItemCount(): Int = data.size

}

