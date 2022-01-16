package com.yaroslavnikolenko.quakereload.ui.DrawerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yaroslavnikolenko.quakereload.ui.Recyclers.MainRecycler
import com.yaroslavnikolenko.quakereload.api.QuakeGetData
import com.yaroslavnikolenko.quakereload.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private var mainRecycler: MainRecycler? = null
    private var viewModel: QuakeGetData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return fragmentMainBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        initViewModel()

    }

    private fun initRecycler(){
        mainRecycler = MainRecycler(){
            findNavController().navigate(MainFragmentDirections.actionDrawerMainToInfoAboutQuakeFragment(it))
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(QuakeGetData::class.java)
        viewModel?.allFieldsOfQuakeApi?.observe(viewLifecycleOwner, Observer {

            mainRecycler?.setApiQuakeList(it)
            fragmentMainBinding?.mainRecycler?.adapter = mainRecycler

            fragmentMainBinding?.progressBar?.visibility = View.INVISIBLE

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }




}