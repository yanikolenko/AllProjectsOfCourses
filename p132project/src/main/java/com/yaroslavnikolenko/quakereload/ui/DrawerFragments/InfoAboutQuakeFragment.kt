package com.yaroslavnikolenko.quakereload.ui.DrawerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.yaroslavnikolenko.quakereload.AdditionalClasses.CountDaysFromDate
import com.yaroslavnikolenko.quakereload.AdditionalClasses.getLevel
import com.yaroslavnikolenko.quakereload.databinding.FragmentInfoAboutQuakeBinding

class InfoAboutQuakeFragment : Fragment() {

    val infoAboutQuakeFragmentArgs: InfoAboutQuakeFragmentArgs by navArgs()
    var infoAboutQuakeBinding: FragmentInfoAboutQuakeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoAboutQuakeBinding = FragmentInfoAboutQuakeBinding.inflate(inflater, container, false)

        return infoAboutQuakeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInfoUi()

    }

    private fun setInfoUi(){
        val dataArg = infoAboutQuakeFragmentArgs.infoAboutQuake

        infoAboutQuakeBinding?.infoFragmentLocationView?.text = dataArg[0]
        infoAboutQuakeBinding?.infoFragmentSensitiveView?.text = getLevel(dataArg[1].toDouble()).title
        infoAboutQuakeBinding?.infoFragmentSensitiveView?.
        setBackgroundColor(infoAboutQuakeBinding?.root?.context!!.getColor(getLevel(dataArg[1].toDouble()).backgroundColor))
        infoAboutQuakeBinding?.infoFragmentTimeView?.text = CountDaysFromDate(dataArg[2]).diff().title
        infoAboutQuakeBinding?.infoFragmentWaveView?.text = String.format("%.1f", dataArg[1].toDouble())
    }
}