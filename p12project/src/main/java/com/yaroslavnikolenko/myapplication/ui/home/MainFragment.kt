package com.yaroslavnikolenko.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaroslavnikolenko.myapplication.FeaturesItem
import com.yaroslavnikolenko.myapplication.api.Quake
import com.yaroslavnikolenko.myapplication.R
import com.yaroslavnikolenko.myapplication.changebleFragment.ChangebleFragment
import com.yaroslavnikolenko.myapplication.databinding.FragmentMainBinding
import com.yaroslavnikolenko.myapplication.recycler.QuakeAdapter

class MainFragment : Fragment() {

    private val quakeInfo by viewModels<Quake>()
    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var recyclerQuake: RecyclerView? = null
    private var recyclerAdapter: QuakeAdapter? = null
    private var progressBar: ProgressBar? = null
    private var bundle = Bundle()
    private var fragment = ChangebleFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        progressBar?.progress

        observeQuakeData()
        initAdapter()

    }

    fun initAdapter(){
        recyclerAdapter = QuakeAdapter(requireContext()){
            sendDataToFragment(it)
        }

        recyclerQuake?.adapter = recyclerAdapter

    }

    fun sendDataToFragment(it: List<String?>){
        bundle.putString("magnitude", it[0])
        bundle.putString("locality", it[1])
        bundle.putString("date", it[2])

        fragment.arguments = bundle
        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit()
    }

    fun observeQuakeData(){
        quakeInfo.resultData.observe(viewLifecycleOwner){
            progressBar?.visibility = View.INVISIBLE
            recyclerAdapter?.dataItem(it)
        }
    }

    fun initUI(){
        progressBar = view?.findViewById(R.id.progressBar)
        recyclerQuake = view?.findViewById(R.id.recyclerWithQuake)
    }

}