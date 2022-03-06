package com.yaroslavnikolenko.myapplication.recyclers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.R
import com.yaroslavnikolenko.myapplication.databinding.AddListsRecyclerItemBinding
import com.yaroslavnikolenko.myapplication.realtimedatabase.data.ToDoListData

class MainRecyclerAdapter(val idOfCheckedList: (ToDoListData, Int) -> Unit): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    private var listTasks = mutableListOf<ToDoListData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setToDoList(listTasks: List<ToDoListData?>){
        this.listTasks = listTasks as MutableList<ToDoListData>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val addListsRecyclerItemBinding = AddListsRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(addListsRecyclerItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUiInfo(position)
    }

    override fun getItemCount(): Int = listTasks.size

    inner class ViewHolder(val binding: AddListsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setUiInfo(number: Int){
            binding.nameOfList.text = listTasks[number].nameOfList
            binding.dateOfList.text = listTasks[number].time
            binding.numberOfList.text = binding.root.context.getString(R.string.count_complete_tasks,
                numberOfCompleteTasks(number), listTasks[number].tasks?.size.toString())

            sendIdOfListElement(number)
        }

        fun sendIdOfListElement(value: Int){
            binding.root.setOnClickListener {
                idOfCheckedList.invoke(listTasks[value], value)

            }
        }

        fun numberOfCompleteTasks(position: Int): String{

            var counter = 0

            listTasks[position].tasks?.forEach {
                if (it[1] == "1"){
                    counter++
                }
            }

            return counter.toString()
        }

    }
}
