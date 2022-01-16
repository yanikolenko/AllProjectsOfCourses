package com.yaroslavnikolenko.quakereload.ui.Recyclers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.quakereload.AdditionalClasses.CountDaysFromDate
import com.yaroslavnikolenko.quakereload.AdditionalClasses.getLevel
import com.yaroslavnikolenko.quakereload.api.FeaturesItem
import com.yaroslavnikolenko.quakereload.databinding.MainRecyclerItemBinding

class MainRecycler(val setInfoAboutCard: (Array<String>) -> Unit): RecyclerView.Adapter<MainRecycler.MyViewHolder>(){

    var allFieldsApiQuake = mutableListOf<FeaturesItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setApiQuakeList(allFieldsApiQuake: List<FeaturesItem?>){
        this.allFieldsApiQuake = allFieldsApiQuake.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mainRecyclerItemBinding = MainRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mainRecyclerItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.setUiInfo(allFieldsApiQuake[position])

    }

    override fun getItemCount(): Int = allFieldsApiQuake.size

    inner class MyViewHolder(val mainRecyclerItemBinding: MainRecyclerItemBinding): RecyclerView.ViewHolder(mainRecyclerItemBinding.root){
        fun setUiInfo(featuresItem: FeaturesItem?) {
            mainRecyclerItemBinding.mainRecyclerLocationView.text = featuresItem?.properties?.locality
            mainRecyclerItemBinding.mainRecyclerSensitiveView.text = getLevel(featuresItem?.properties?.magnitude!!).title
            mainRecyclerItemBinding.mainRecyclerSensitiveView.
                setBackgroundColor(mainRecyclerItemBinding.root.context.getColor(getLevel(featuresItem.properties.magnitude).backgroundColor))
            mainRecyclerItemBinding.mainRecyclerTimeView.text = CountDaysFromDate(featuresItem.properties.time!!).diff().title
            mainRecyclerItemBinding.mainRecyclerWaveView.text = String.format("%.1f", featuresItem.properties.magnitude)

            setInfoToArray(featuresItem)
        }

        fun setInfoToArray(featuresItem: FeaturesItem?){
            mainRecyclerItemBinding.root.setOnClickListener {

                val sendInfo = arrayOf(featuresItem?.properties?.locality!!, featuresItem.properties.magnitude!!.toString(),
                    featuresItem.properties.time!!)

                setInfoAboutCard(sendInfo)

            }
        }
    }
}