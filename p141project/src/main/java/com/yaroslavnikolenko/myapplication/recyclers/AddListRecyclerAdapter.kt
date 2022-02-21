package com.yaroslavnikolenko.myapplication.recyclers

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.databinding.AddTasksRecyclerItemBinding
import java.lang.NullPointerException

class AddListRecyclerAdapter(val idOfCheckedBox: (Int, Int) -> Unit): RecyclerView.Adapter<AddListRecyclerAdapter.ViewHolder>() {

    var listOfTasks = mutableListOf<String>()
    var listOfStatus = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setDataListOfTasks(listOfTasks: MutableList<String>){
        this.listOfTasks = listOfTasks
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataStatusOfTasks(listOfStatus: MutableList<String>){
        this.listOfStatus = listOfStatus
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val addTasksRecyclerItemBinding = AddTasksRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(addTasksRecyclerItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUiInfo(position)

    }

    override fun getItemCount(): Int = listOfTasks.size

    inner class ViewHolder(val binding: AddTasksRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setUiInfo(number: Int){
            binding.taskTextRecycler.text = listOfTasks[number]

            try {
                if (listOfStatus[number] == "1"){
                    binding.checkBoxRecycler.isChecked = true
                    binding.taskTextRecycler.paintFlags = binding.taskTextRecycler.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }else{
                    binding.checkBoxRecycler.isChecked = false
                    binding.checkBoxRecycler.paintFlags = binding.checkBoxRecycler.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }catch (e:NullPointerException){}

            sendIdOfBox(number)
        }

        fun sendIdOfBox(number: Int){
            binding.checkBoxRecycler.setOnClickListener {
                idOfCheckedBox(binding.checkBoxRecycler.isChecked.compareTo(false), number)

                if (binding.checkBoxRecycler.isChecked){
                    binding.taskTextRecycler.paintFlags = binding.taskTextRecycler.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }else{
                    binding.checkBoxRecycler.paintFlags = binding.checkBoxRecycler.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }

            }
        }
    }
}