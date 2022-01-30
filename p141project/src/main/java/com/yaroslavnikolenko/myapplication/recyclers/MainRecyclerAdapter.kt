package com.yaroslavnikolenko.myapplication.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.Converter.ConverterTaskList
import com.yaroslavnikolenko.myapplication.R
import com.yaroslavnikolenko.myapplication.data.db.model.UserEntity
import com.yaroslavnikolenko.myapplication.data.repository.UserRepository
import com.yaroslavnikolenko.myapplication.databinding.AddListsRecyclerItemBinding

class MainRecyclerAdapter(val idOfCheckedList: (UserEntity) -> Unit): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    private var listTasks: UserRepository = UserRepository()
    private var size = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val addListsRecyclerItemBinding = AddListsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(addListsRecyclerItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUiInfo(position)
    }

    override fun getItemCount(): Int = listTasks.getUser().size

    inner class ViewHolder(val binding: AddListsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setUiInfo(number: Int){
            binding.nameOfList.text = listTasks.getUser()[number].nameOfList
            binding.dateOfList.text = listTasks.getUser()[number].time
            binding.numberOfList.text = binding.root.context.getString(R.string.count_complete_tasks, numberOfCompleteTasks(number).toString(), ConverterTaskList.toCompleteList(listTasks.getListForId(listTasks.getUser()[number].id)!!.complete).size.toString())

            sendIdOfListElement(number)
        }

        fun sendIdOfListElement(value: Int){
            binding.root.setOnClickListener {
                idOfCheckedList.invoke(listTasks.getUser()[value])

            }
        }

        fun numberOfCompleteTasks(position: Int): Int{

            var counter = 0

            ConverterTaskList.toCompleteList(listTasks.getListForId(listTasks.getUser()[position].id)?.complete!!).forEach {
                size++
                if (it == "1"){
                    counter++
                }
            }

            return counter
        }

    }
}
