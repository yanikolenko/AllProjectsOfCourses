package com.yaroslavnikolenko.alotrecyclers.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yaroslavnikolenko.alotrecyclers.R
import com.yaroslavnikolenko.alotrecyclers.data.Chat
import com.yaroslavnikolenko.alotrecyclers.data.ChatViewModel
import java.lang.NullPointerException

class AddSomeInfo() : Fragment() {

    lateinit var confirm: Button
    lateinit var chatViewModel: ChatViewModel
    lateinit var name: EditText
    lateinit var massage: EditText
    lateinit var chat: Chat
    lateinit var chat1: Chat
    lateinit var image: ImageView
    lateinit var addPhotoCamera: FloatingActionButton
    var bitmap: Bitmap? = null

    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleCameraImage(result.data)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflater = inflater.inflate(R.layout.fragment_add_some_info, container, false)
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        confirm = inflater.findViewById(R.id.sendInfo)
        name = inflater.findViewById(R.id.nameEdit)
        massage = inflater.findViewById(R.id.massageEdit)
        image = inflater.findViewById(R.id.image)
        addPhotoCamera = inflater.findViewById(R.id.addPhotoCamera)

        addPhotoCamera.setOnClickListener {
            makeOrDownload()
         }

        confirm.setOnClickListener {
            sendDataToDatabase()
        }

        return  inflater
    }

    private fun sendDataToDatabase() {
        val nameValue = name.text.toString()
        val massageValue = massage.text.toString()
        try {

            if(nameValue == "" || massageValue == ""){
                Toast.makeText(requireContext(), "Please check the fields !", Toast.LENGTH_SHORT).show()
            }else{
                chat = Chat(0, nameValue, massageValue, bitmap!!)
                chatViewModel.addChat(chat)
                Toast.makeText(requireContext(), chat.massage, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addSomeInfo_to_fifthFragment)
            }

        }catch (e: NullPointerException){
            Toast.makeText(requireContext(), "Please check the photo !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleCameraImage(intent: Intent?) {
        bitmap = intent?.extras?.get("data") as Bitmap
        image.setImageBitmap(bitmap)

    }

    fun makeOrDownload(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(cameraIntent)

    }



}