package com.yaroslavnikolenko.myapplication.changebleFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yaroslavnikolenko.myapplication.additionalClasses.ChooseText
import com.yaroslavnikolenko.myapplication.additionalClasses.CountDaysAgo
import com.yaroslavnikolenko.myapplication.R
import com.yaroslavnikolenko.myapplication.constants.QuakeApiConsts
import java.text.SimpleDateFormat

class ChangebleFragment : Fragment() {

    var changebleDate: TextView? = null
    var changebleSide: TextView? = null
    var changebleSensitive: TextView? = null
    var changebleSizeQuake: TextView? = null
    var locality: Any? = null
    var magnitude:Any? = null
    var formattedDay: Int? = null
    var formattedMonth: Int? = null
    var reformMagnitude: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_changeble, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        workWithDate()

        setUiInfo()

    }

    private fun returnDiffOfDays(month: Int, day: Int): String = CountDaysAgo(month, day).diff()

    @SuppressLint("SimpleDateFormat")
    private fun workWithDate(){
        val args = this.arguments
        magnitude = args?.get("magnitude")
        locality = args?.get("locality")
        val date = args?.get("date").toString()

        val sdf = SimpleDateFormat(QuakeApiConsts.TIME_STANDART)
        val day = SimpleDateFormat("dd")
        val month = SimpleDateFormat("MM")
        val formatDay = sdf.parse(date)
        val formatMonth = sdf.parse(date)
        formattedDay = day.format(formatDay).toInt()
        formattedMonth = month.format(formatMonth).toInt()
    }

    private fun setUiInfo(){
        reformMagnitude = magnitude.toString().replace(",", ".").toDouble()

        changebleDate?.text = when(returnDiffOfDays(formattedMonth!!, formattedDay!!)){
            "Today" -> returnDiffOfDays(formattedMonth!!, formattedDay!!)
            else -> getString(R.string.date_reform, returnDiffOfDays(formattedMonth!!, formattedDay!!))
        }
        changebleSide?.text = locality.toString()
        changebleSizeQuake?.text = magnitude.toString()

        ChooseText(requireContext()).chooseText(reformMagnitude!!, changebleSensitive!!)
    }

    private fun initUI(){
        changebleDate = view?.findViewById(R.id.changebleDateView)
        changebleSide = view?.findViewById(R.id.changebleSideView)
        changebleSensitive = view?.findViewById(R.id.changebleSensitiveView)
        changebleSizeQuake = view?.findViewById(R.id.changebleSizeQuakeView)
    }

}